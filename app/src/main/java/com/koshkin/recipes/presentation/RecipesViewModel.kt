package com.koshkin.recipes.presentation

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

class RecipesViewModel(
    private val getRecipeInfo: GetRecipeInfo,
    private val getRemoteRecipes: GetRemoteRecipes
) :ViewModel(){
    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _recipes = MutableLiveData<List<Results>>() //TODO сделать "энтити" для презентэйшн и переписать _recipes
    val recipes = _recipes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _remoteRecipes = arrayListOf<Results>()

    private var _remoteRecipeInfo:Results? =null

    fun getRecipes(tag: String?, ingredient: String?){
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when(val recipesResult = getRemoteRecipes.invoke(tag,ingredient)){
                is Result.Success ->{
                    _remoteRecipes.clear()
                    _remoteRecipes.addAll(recipesResult.data)

                    recipes.value =_remoteRecipes     //TODO сделать через мапер энтити презентейшн
                    _dataLoading.postValue(false)
                }

                is Result.Error ->{
                    _dataLoading.postValue(false)
                    _error.postValue(recipesResult.exception.message)
                }
            }
        }
    }

    fun getInfoRecipe(recipeID: Int){
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when(val recipesResult = getRecipeInfo.invoke(recipeID)){
                is Result.Success ->{
                    _remoteRecipes.clear()
                    _remoteRecipeInfo= recipesResult.data

                    recipes.value = _remoteRecipes            //TODO сделать через мапер энтити презентейшн
                    _dataLoading.postValue(false)
                }

                is Result.Error ->{
                    _dataLoading.postValue(false)
                    _error.postValue(recipesResult.exception.message)
                }
            }
        }
    }

    class RecipesViewModelFactory(
        private val getRemoteRecipes: GetRemoteRecipes,
        private val getRecipeInfo: GetRecipeInfo
    ): ViewModelProvider.NewInstanceFactory(){

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RecipesViewModel(
                getRecipeInfo,
                getRemoteRecipes
            ) as T
        }
    }


}