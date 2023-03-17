package com.koshkin.recipes.presentation

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.koshkin.recipes.R
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.domain.usecases.GetRecipeInfo
import com.koshkin.recipes.domain.usecases.GetRemoteRecipes
import kotlinx.coroutines.launch
import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.KeyTrans
import com.koshkin.recipes.domain.usecases.GetKey
import com.koshkin.recipes.domain.usecases.PostRecipe
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import okhttp3.RequestBody
import java.text.SimpleDateFormat
import java.util.*

class RecipesViewModel(
    private val getRecipeInfo: GetRecipeInfo,
    private val getRemoteRecipes: GetRemoteRecipes,
    private val postRecipe: PostRecipe,
    private val getKey: GetKey
) :ViewModel(){


    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _allowRequest = MutableLiveData(true)
    val allowRequest: LiveData<Boolean> = _allowRequest

    private val _recipes = MutableLiveData<List<Results>>() //TODO сделать "энтити" для презентэйшн и переписать _recipes
    val recipes = _recipes

    //private val _oneRecipes = MutableLiveData<Results>() //TODO сделать "энтити" для презентэйшн и переписать _recipes
    var oneRecipes :Results? =null

    var keyTrans: KeyTrans? = null

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _remoteRecipes = arrayListOf<Results>()

     var remoteRecipeInfo:Results? =null


    fun getRecipes(from: Int,size: Int,tag: String?, ingredient: String?){
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when(val recipesResult = getRemoteRecipes.invoke(from,size,tag,ingredient)){
                is Result.Success ->{
                //    _remoteRecipes.clear()
                    _remoteRecipes.addAll(recipesResult.data)

                    recipes.value =_remoteRecipes     //TODO сделать через мапер энтити презентейшн
                    _dataLoading.postValue(false)

                    if(_remoteRecipes.size<20)        // походу проверка на конец списка не ныжна TODO проверить
                        _allowRequest.postValue(false)
                }

                is Result.Error ->{
                    _dataLoading.postValue(false)
                    _error.postValue(recipesResult.exception.message)
                    Log.i("RVM_ERR",recipesResult.exception.message.toString())
                }
            }
        }
     //   return allowRequest
    }

    suspend fun getInfoRecipe(recipeID: Int){
        Log.i("RVM",recipeID.toString())
    val job=    viewModelScope.launch {
            Log.i("RVM","launch")
            _dataLoading.postValue(true)
            when(val recipesResult = getRecipeInfo.invoke(recipeID)){

                is Result.Success ->{
          //          _remoteRecipes.clear()
                    oneRecipes=recipesResult.data!!

            //        oneRecipes = _remoteRecipeInfo           //TODO сделать через мапер энтити презентейшн
                    _dataLoading.postValue(false)
                }

                is Result.Error ->{
                    _dataLoading.postValue(false)
                    _error.postValue(recipesResult.exception.message)
                }
            }
        }
        job.join()
    }

    suspend fun postRecipe(requestBody: RequestBody): Int{
       val deferred = viewModelScope.async {
                postRecipe.invoke(requestBody)
        }
        return  deferred.await()
    }

    suspend fun getKey(){
       val job = viewModelScope.launch {
           when(val keyResult = getKey.invoke()){
               is Result.Success ->{
                   keyTrans = keyResult.data
               }
               is Result.Error ->{
                   _error.postValue(keyResult.exception.message)
               }
           }
        }
         job.join()
    }


    class RecipesViewModelFactory(
        private val getRemoteRecipes: GetRemoteRecipes,
        private val getRecipeInfo: GetRecipeInfo,
        private val postRecipe: PostRecipe,
        private var getKey: GetKey
    ): ViewModelProvider.NewInstanceFactory(){

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RecipesViewModel(
                getRecipeInfo,
                getRemoteRecipes,
                postRecipe,
                getKey
            ) as T
        }
    }


}