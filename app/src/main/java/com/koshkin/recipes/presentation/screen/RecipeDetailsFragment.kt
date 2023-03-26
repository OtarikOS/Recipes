package com.koshkin.recipes.presentation.screen

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.koshkin.recipes.App
import com.koshkin.recipes.R
import com.koshkin.recipes.data.repositories.RecipesRemoteDataSourceImp
import com.koshkin.recipes.data.repositories.RecipesRepositoryImpl
import com.koshkin.recipes.databinding.FragmentRecipeDetailsBinding
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.domain.transformation.ConvertedResults
import com.koshkin.recipes.domain.usecases.PostRecipe
import com.koshkin.recipes.presentation.RecipesViewModel
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.http.Header
import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.KeyT
import com.koshkin.recipes.domain.entity.TransRequestBody
import com.koshkin.recipes.presentation.MAIN
import kotlinx.serialization.encodeToString
import java.util.prefs.Preferences

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
    private val KEY = "key"

    private  var recipeRead: Results? = null

    private var keyTrans: KeyT? = null

    private var sPref: SharedPreferences = MAIN.getPreferences(Context.MODE_PRIVATE)
    private val editor:SharedPreferences.Editor = sPref.edit()

    private lateinit var converter: ConvertedResults

  //  private val postRecipe: PostRecipe =((activity?.application) as App).postRecipe

    private val recipesViewModel: RecipesViewModel by activityViewModels()

    // TODO: Rename and change types of parameters
    private var idRecipe: Int? = null
    private var recipe: String? = null

    private var key:String? = null

    private var str: ArrayList<String> = arrayListOf()

    private var transRequestBody:TransRequestBody = TransRequestBody()

    private var strRecipe:String? = null

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

        key = sPref.getString("key_translate","")
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

        binding.button.setOnClickListener {



            val bundle=Bundle()
            bundle.putString("recipe",strRecipe)
            Log.i("RIF",recipeRead?.id!!.toString())
            MAIN.navController.navigate(R.id.action_recipeDetailsFragment_to_recipeInfoFragment,bundle)
        }

    //    recipesViewModel.getInfoRecipe(idRecipe!!)
//        binding.button.setOnClickListener{
//            var result:Int? = null
////            val jsonObject = JSONObject()
////            jsonObject.put("id",recipeRead!!.id)
////
////            Log.i("POST_RDF", recipeRead!!.id.toString())
//
//            val requestBody = recipe!!.toRequestBody("application/json".toMediaTypeOrNull())
//            CoroutineScope(Dispatchers.Main).launch {
//                Log.i("REQUEST", requestBody.toString())
//                val def = async {
//                    recipesViewModel.postRecipe(requestBody)
//                }
//                result = def.await()
//
//
//                Log.i("As_RDF", result.toString())
//                var message: Int? = null
//                if (result == 200)
//                    message = R.string.toast_200
//                if (result == 500)
//                    message = R.string.toast_500
//                if (result == 229)
//                    message = R.string.toast_gut
//                if (result == 470)
//                    message = R.string.toast_nodb
//                if (result == 477)
//                    message = R.string.toast_exists
//
//                Toast.makeText(context, message!!, Toast.LENGTH_SHORT).show()
//            }
//        }



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

        binding.btnKey.setOnClickListener{
            binding.pbLoading2.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.Main).launch{
                val def =async {
                    recipesViewModel.getKey()
                }
                def.await()
                keyTrans = recipesViewModel.keyTrans
                binding.pbLoading2.visibility = View.GONE

                if (keyTrans?.key!=null) {
                    Log.i("RDF_key",keyTrans?.key!!)
                    editor.putString("key_translate", keyTrans?.key.toString())
                    editor.commit()
                    key = keyTrans?.key
                    Toast.makeText(context, "Ключ есть", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(context, "Проблема с ключом", Toast.LENGTH_SHORT).show()
                }
            }
        }

    //    val body:RequestBody = RequestBody.create()
        binding.btnTrans.setOnClickListener {
                //вспомогатель string временный вместо Result<out R>
            val string = recipeRead?.name

            binding.pbLoading2.visibility = View.VISIBLE
            converter = ConvertedResults(recipeRead!!)
            str = converter.toArrayForRequest()
            Log.i("RDF_convert", str.toString())

            transRequestBody.folderId = "b1gdreu1lvbb8dm0pdq7"
            transRequestBody.texts = str
            transRequestBody.targetLanguageCode = "ru"

            val json = Json
            val requestBody = json.encodeToString(transRequestBody).toRequestBody()

            Log.i("Trans_RDF_Body", requestBody.toString())



            CoroutineScope(Dispatchers.Main).launch {
                Log.i("REQUEST", requestBody.toString())
                val def = async {
                    recipesViewModel.translate("Bearer $key", requestBody)
                }
                val result = def.await()
                binding.pbLoading2.visibility =View.GONE

                if(result.translations.isEmpty()) {
                    Toast.makeText(context,"Возможно ключ устарел",Toast.LENGTH_LONG).show()

                }else {

                    Log.i("Trans_RDF", result.toString())
                    converter.fromBodyResponse(result.translations,recipeRead!!)
                    Log.i("Trans_RDF", recipeRead.toString())

                    Toast.makeText(context,"Возможно ключ устарел",Toast.LENGTH_LONG).show()
                    binding.tvR.text = recipeRead?.name
                }
            //    val json = Json
                strRecipe = json.encodeToString(recipeRead)
            }
        }


    }


}