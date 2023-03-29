package com.koshkin.recipes.presentation

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.koshkin.recipes.App
import kotlinx.coroutines.launch
import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.*
import com.koshkin.recipes.domain.entity.sent.SentIdDomain
import com.koshkin.recipes.domain.usecases.*
import com.koshkin.recipes.presentation.sent.RecipeWithStatusMapper
import com.koshkin.recipes.presentation.sent.RecipesForFragmentWithStatus
import kotlinx.coroutines.async
import okhttp3.RequestBody
import java.io.File

class RecipesViewModel(
    private val getRecipeInfo: GetRecipeInfo,
    private val getRemoteRecipes: GetRemoteRecipes,
    private val postRecipe: PostRecipe,
    private val getKey: GetKey,
    private val getTranslate: GetTranslate,
    private val deleteAll: DeleteAll,
    private val getSavedRecipes: GetSavedRecipes,
    private val savedRecipes: SaveAllRecipes,
    private val saveSent: SaveSent,
    private val getSent: GetSent,
    private val mapper: RecipeWithStatusMapper
) :ViewModel(){

    private var recipeWithStatusFlow:List<SentIdDomain>? = null
    private val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _allowRequest = MutableLiveData(true)
    val allowRequest: LiveData<Boolean> = _allowRequest

    private val _recipes = MutableLiveData<List<RecipesForFragmentWithStatus>>() //TODO сделать "энтити" для презентэйшн и переписать _recipes
    val recipes = _recipes

    //private val _oneRecipes = MutableLiveData<Results>() //TODO сделать "энтити" для презентэйшн и переписать _recipes
    var oneRecipes :Results? =null

    var keyTrans: KeyT? = null

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var _remoteRecipes = arrayListOf<RecipesForFragment>()
   // @Volatile
    var recipeDb = _remoteRecipes

     var remoteRecipeInfo:Results? =null


    fun getRecipes(from: Int,size: Int,tag: String?, ingredient: String?){
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when(val recipesResult = getRemoteRecipes.invoke(from,size,tag,ingredient)){
                is Result.Success ->{
                //    _remoteRecipes.clear()
                    _remoteRecipes.addAll(recipesResult.data)

                     recipeWithStatusFlow = getSent.invoke()
//                        //      recipeWithStatusFlow.collect { recipesFlow ->         //TODO сделать через мапер энтити презентейшн
                        recipes.postValue(mapper.fromRecipeToRecipeWithStatus(_remoteRecipes,
                            recipeWithStatusFlow!!
                        ))
                    Log.i("RVM_recipes",recipes.value.toString())
                        recipeDb = _remoteRecipes
                        _dataLoading.postValue(false)
            //        }
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
 //       val recipeWithStatusFlow = getSent.invoke()
        //      recipeWithStatusFlow.collect { recipesFlow ->         //TODO сделать через мапер энтити презентейшн
        //  recipes.value
      //  val s   = mapper.fromRecipeToRecipeWithStatus(_remoteRecipes,recipeWithStatusFlow!!)
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
       val def = viewModelScope.launch {
           when (val keyResult =
               getKey.invoke()) {
               is Result.Success -> {
                   keyTrans = keyResult.data as KeyT?
                   //                 Log.i("RVM_key_success", keyResult.data.toString())
               }
               is Result.Error -> {
                   _error.postValue(keyResult.exception.message)
                                    Log.i("RVM_ERR_key_error",_error.toString())
               }
           }

       }
        def.join()
      //  return keyTrans
    }

    suspend fun translate(authorizationKey:String ,requestBody: RequestBody): Translate{
        val deferred = viewModelScope.async {
            getTranslate.invoke(authorizationKey,requestBody)
        }
        return  deferred.await()
    }

    suspend fun deleteAll(){
        val job = viewModelScope.launch{
            deleteAll.invoke()
        }
        job.join()
    }

    suspend fun getSavedRecipes(){
   // : List<RecipesForFragment>{
   //     val job = viewModelScope.launch {
        val def = viewModelScope.async{
            _dataLoading.postValue(true)
            val job2 =launch {
                recipeWithStatusFlow = getSent.invoke()
            }
            job2.join()
            getSavedRecipes.invoke()


        }
        _remoteRecipes = def.await() as ArrayList<RecipesForFragment>
        Log.i("RVM_getSaved",_remoteRecipes.toString())

        recipeDb = _remoteRecipes



        Log.i("RVM_flow","start")
    //    recipeWithStatusFlow.collect { recipesFlow ->         //TODO сделать через мапер энтити презентейшн
            recipes.value =mapper.fromRecipeToRecipeWithStatus(_remoteRecipes, recipeWithStatusFlow!!)
            //   recipes.postValue(_remoteRecipes)
            Log.i("RVM_flow","recipes ${recipes.value!!.size} _remote ${_remoteRecipes.size} withs ${recipeWithStatusFlow!!.size}")
//            Log.i("RVM_flow", recipes.value!![0].id.toString())
            _dataLoading.postValue(false)
     //   }
        Log.i("RVM_flow2","end")

        if (recipeDb.size>0){
            Log.i("RVM_getSaved",recipeDb.size.toString())
            _dataLoading.postValue(false)
        }
//        }
//        job.join()
    //    _dataLoading.postValue(false)
     //   return recipeDb
            //recipeDb
    }

    suspend fun saveAllRecipes(recipes: List<RecipesForFragment>){
        val job = viewModelScope.launch{
            savedRecipes.invoke(recipes)
        }
        job.join()
    }

    suspend fun insertSentId(id:Results) {
        val job = viewModelScope.launch {

            saveSent.invoke(mapper.resultsToSendIdDomain(id))
        }
            job.join()


//        val job2 = launch { recipeWithStatusFlow = getSent.invoke() }
//            job2.join()
//            recipes.postValue(mapper.fromRecipeToRecipeWithStatus(_remoteRecipes, recipeWithStatusFlow!!))

      //  }

    }

    fun databaseFileExists(context: Context): Boolean {
    //    val context:Context =
        return try {
            File(context.getDatabasePath("ScreenDataBase").absolutePath).exists()
        }catch (e: Exception){
            false
        }
    }


    class RecipesViewModelFactory(
        private val getRemoteRecipes: GetRemoteRecipes,
        private val getRecipeInfo: GetRecipeInfo,
        private val postRecipe: PostRecipe,
        private val getKey: GetKey,
        private val getTranslate: GetTranslate,
        private val deleteAll: DeleteAll,
        private val getSavedRecipes: GetSavedRecipes,
        private val savedRecipes: SaveAllRecipes,
        private val saveSent: SaveSent,
        private val getSent: GetSent,
        private val mapper: RecipeWithStatusMapper
    ): ViewModelProvider.NewInstanceFactory(){

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RecipesViewModel(
                getRecipeInfo,
                getRemoteRecipes,
                postRecipe,
                getKey,
                getTranslate,
                deleteAll, getSavedRecipes, savedRecipes,
                saveSent, getSent,
                mapper
            ) as T
        }
    }


}