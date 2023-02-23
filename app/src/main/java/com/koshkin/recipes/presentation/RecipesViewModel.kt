package com.koshkin.recipes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _remoteRecipes = arrayListOf<Results>()

    fun getRecipes(tag: String?, ingredient: String?){
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when(val recipesResult = getRemoteRecipes.invoke(tag,ingredient)){
                is Result.Success ->{
                    _remoteRecipes.clear()
                    _remoteRecipes.addAll(recipesResult.data)
                }
            }
        }
    }
}