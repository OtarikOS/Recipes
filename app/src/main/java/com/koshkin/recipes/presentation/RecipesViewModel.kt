package com.koshkin.recipes.presentation

import android.util.Log
import androidx.lifecycle.*
import com.koshkin.recipes.domain.entity.RecipesForFragment
import com.koshkin.recipes.domain.entity.Results
import kotlinx.coroutines.launch
import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.KeyTrans
import com.koshkin.recipes.domain.entity.Translate
import com.koshkin.recipes.domain.usecases.*
import kotlinx.coroutines.async
import okhttp3.RequestBody

class RecipesViewModel(
    private val getRecipeInfo: GetRecipeInfo,
    private val getRemoteRecipes: GetRemoteRecipes,
    private val postRecipe: PostRecipe,
    private val getKey: GetKey,
    private val getTranslate: GetTranslate
) :ViewModel(){


    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _allowRequest = MutableLiveData(true)
    val allowRequest: LiveData<Boolean> = _allowRequest

    private val _recipes = MutableLiveData<List<RecipesForFragment>>() //TODO сделать "энтити" для презентэйшн и переписать _recipes
    val recipes = _recipes

    //private val _oneRecipes = MutableLiveData<Results>() //TODO сделать "энтити" для презентэйшн и переписать _recipes
    var oneRecipes :Results? =null

    var keyTrans: KeyTrans? = null

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _remoteRecipes = arrayListOf<RecipesForFragment>()

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

    suspend fun getKey():KeyTrans{
       val def = viewModelScope.async {
//           when(val keyResult =
               getKey.invoke()
//               is Result.Success ->{
//                   keyTrans = keyResult.data
//  //                 Log.i("RVM_key_success", keyResult.data.toString())
//               }
//               is Result.Error ->{
//                   _error.postValue(keyResult.exception.message)
//  //                 Log.i("RVM_ERR_key_error",keyResult.exception.message.toString())
//               }
//           }
        }
         return def.await()
    }

    suspend fun translate(authorizationKey:String ,requestBody: RequestBody): Translate{
        val deferred = viewModelScope.async {
            getTranslate.invoke(authorizationKey,requestBody)
        }
        return  deferred.await()
    }


    class RecipesViewModelFactory(
        private val getRemoteRecipes: GetRemoteRecipes,
        private val getRecipeInfo: GetRecipeInfo,
        private val postRecipe: PostRecipe,
        private var getKey: GetKey,
        private val getTranslate: GetTranslate
    ): ViewModelProvider.NewInstanceFactory(){

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RecipesViewModel(
                getRecipeInfo,
                getRemoteRecipes,
                postRecipe,
                getKey,
                getTranslate
            ) as T
        }
    }


}