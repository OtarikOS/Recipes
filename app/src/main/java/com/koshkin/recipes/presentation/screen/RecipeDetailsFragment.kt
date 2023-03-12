package com.koshkin.recipes.presentation.screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.koshkin.recipes.App
import com.koshkin.recipes.data.repositories.RecipesRemoteDataSourceImp
import com.koshkin.recipes.data.repositories.RecipesRepositoryImpl
import com.koshkin.recipes.databinding.FragmentRecipeDetailsBinding
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.domain.usecases.PostRecipe
import com.koshkin.recipes.presentation.RecipesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM = "Id"
private const val ARG_PARAM2 = "recipe"

/**
 * A simple [Fragment] subclass.
 * Use the [RecipeDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeDetailsFragment(/*private val postRecipe: PostRecipe*/) : Fragment() {
    private lateinit var binding: FragmentRecipeDetailsBinding

    private  var recipeRead: Results? = null

  //  private val postRecipe: PostRecipe =((activity?.application) as App).postRecipe

    private val recipesViewModel: RecipesViewModel by activityViewModels()

    // TODO: Rename and change types of parameters
    private var idRecipe: Int? = null
    private var recipe: String? = null

   // private val postRecipe: PostRecipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
      //      idRecipe = it.getInt(ARG_PARAM)
            recipe = it.getString(ARG_PARAM2)
        }
        val json = Json
        recipeRead = json.decodeFromString(recipe!!)
//        recipesViewModel.getInfoRecipe(idRecipe!!)
//        Log.i("RDF",idRecipe.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    //    recipesViewModel.getInfoRecipe(idRecipe!!)
        binding.button.setOnClickListener{
            val jsonObject = JSONObject()
            jsonObject.put("id",recipeRead!!.id)

            Log.i("POST_RDF", recipeRead!!.id.toString())

            val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
            CoroutineScope(Dispatchers.IO).launch {
                Log.i("REQUEST", requestBody.toString())
                val def = async {
                    recipesViewModel.postRecipe(requestBody)
                }
                val result = def.await()
                Log.i("As_RDF",result.toString())
            }
        }

        recipesViewModel.dataLoading.observe(viewLifecycleOwner, { loading ->
            when (loading) {
                true -> {binding.pbLoading2.visibility = View.VISIBLE}
                false -> {binding.pbLoading2.visibility = View.GONE}
            }
        })

        binding.tvR.text = recipeRead?.name
            //recipesViewModel.remoteRecipeInfo!!.instructions[0].displayText

//        val res = recipesViewModel.recipes     // oneRecipes
//         res.observe(viewLifecycleOwner,{
//             binding.tvR.text = recipeRead!!.instructions[0].displayText.toString()
//             Log.i("RDF_ovc",recipeRead!!.instructions[0].displayText.toString())
//         })


    }
}