package com.koshkin.recipes.presentation.screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.koshkin.recipes.R
import com.koshkin.recipes.databinding.FragmentRecipeInfoBinding
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.presentation.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody


private const val ARG_PARAM = "recipe"


class RecipeInfoFragment : Fragment() {
    private lateinit var infoAdapter: InfoAdapter
    private var recipe: String? = null


    private  var recipeRead:Results? = null

  //  private var adapter:ArrayAdapter<String>? =null
    private  var  content:ArrayList<String> = arrayListOf()
    private var contentCount:ArrayList<String> = arrayListOf()

    private lateinit var binding: FragmentRecipeInfoBinding

    private var changeConstraint: ConstraintSet? =null






    private val recipesViewModel: RecipesViewModel by activityViewModels()

    // var info= List<Results>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val json = Json
            recipe = it.getString(ARG_PARAM)
            Log.i("RIF_OC",recipe.toString())
            recipeRead = recipe?.let { json.decodeFromString(it) }!!

        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRecipeInfoBinding.inflate(layoutInflater, container, false)
        return binding.root

    //    adapter = ArrayAdapter<String>(MAIN,R.layout.list_component,R.id.list_content,content)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.tvButton.setOnClickListener {
//
//
//
//            val bundle=Bundle()
//            bundle.putString("recipe",recipe)
//            Log.i("RIF",recipeRead?.id!!.toString())
//            MAIN.navController.navigate(R.id.action_recipeInfoFragment_to_recipeDetailsFragment,bundle)
//        }
//                                       // Отправка
                binding.tvButton.setOnClickListener{
            var result:Int? = null
//            val jsonObject = JSONObject()
//            jsonObject.put("id",recipeRead!!.id)
//
//            Log.i("POST_RDF", recipeRead!!.id.toString())

                               // сохранение измененого названия

                    recipeRead!!.name = binding.infoName.text.toString()

                                           // сохранение описания

                    if(recipeRead?.aspectRatio.toString().equals("9:16")){
                        recipeRead!!.description = binding.infoDescription169.text.toString()
                    } else if(recipeRead?.aspectRatio!=null){
                        recipeRead!!.description = binding.infoDescription.text.toString()
                    }

                                    // сохранение инструкций

                    for(i in 0 until recipeRead!!.instructions.size){
                        recipeRead!!.instructions[i].displayText = content[i]
                        //          contentCount.add((i+1).toString())
                    }
       //             recipeRead!!.name =  editorRecipe!!.edition()
                    val json = Json
                    recipe =json.encodeToString(recipeRead)

            val requestBody = recipe!!.toRequestBody("application/json".toMediaTypeOrNull())
            CoroutineScope(Dispatchers.Main).launch {
                Log.i("REQUEST", requestBody.toString())
                val def = async {
                    recipesViewModel.postRecipe(requestBody)
                }
                result = def.await()


                Log.i("As_RDF", result.toString())
                var message: Int? = null
                if (result == 200)
                    message = R.string.toast_200
                if (result == 500)
                    message = R.string.toast_500
                if (result == 229)
                    message = R.string.toast_gut
                if (result == 470)
                    message = R.string.toast_nodb
                if (result == 477)
                    message = R.string.toast_exists

                Toast.makeText(context, message!!, Toast.LENGTH_SHORT).show()
                Log.i("RIF_json",recipe!!)
            }
                    Log.i("RIF_json_2",recipe!!)
        }


        binding.nutritionLinearLayout.setOnClickListener{
            if(binding.nutritionTableInfo.visibility==View.GONE){
                binding.nutritionTableInfo.visibility = View.VISIBLE
                binding.ivMore.setImageResource(R.drawable.ic_baseline_expand_more_24)
            }else{
                binding.nutritionTableInfo.visibility =View.GONE
                binding.ivMore.setImageResource(R.drawable.ic_baseline_expand_less_24)
            }
        }


        recipeRead!!.thumbnailUrl.let { imageUrl ->
            Glide.with(requireContext())
                .load(imageUrl)
                .into(binding.ivRecipeInfo)
        }

        binding.infoName.setText(recipeRead?.name)
     //   editorRecipe = EditorRecipe(binding.infoName,recipeRead?.name.toString())
       // recipeRead!!.name =  editorRecipe!!.edition()

        if(recipeRead?.aspectRatio.toString().equals("9:16")){
            binding.infoDescription169.visibility = View.VISIBLE
            binding.infoDescription169.setText(recipeRead?.description)
        } else if(recipeRead?.aspectRatio!=null){
            binding.infoDescription.visibility = View.VISIBLE
            binding.infoDescription.setText(recipeRead?.description)
        }

        for(i in 0 until recipeRead!!.instructions.size){
            content.add(recipeRead!!.instructions[i].displayText.toString())
  //          contentCount.add((i+1).toString())
        }

        if(recipeRead!!.nutrition?.calories == null)
            binding.nutritionLinearLayout.visibility = View.GONE
        else{
            binding.tvCaloriesInfo.text = recipeRead!!.nutrition?.calories.toString()
            binding.tvProteinInfo.text = recipeRead!!.nutrition?.protein.toString()
            binding.tvSugarInfo.text = recipeRead!!.nutrition?.sugar.toString()
            binding.tvFiberInfo.text = recipeRead!!.nutrition?.fiber.toString()
            binding.tvCarbohydratesInfo.text = recipeRead!!.nutrition?.carbohydrates.toString()
            binding.tvFatInfo.text = recipeRead!!.nutrition?.fat.toString()
            Log.i("RIF", recipeRead!!.nutrition?.calories.toString())
        }

   //     adapter = ArrayAdapter<String>(MAIN,R.layout.list_component,R.id.list_content,content)
        binding.recyclerIngredientBase.adapter =BaseIngredientAdapter(requireContext(),recipeRead!!.sections)

        binding.infoList.apply {
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = InfoAdapter(requireContext(),content)
        }
        Log.i("RIF",content.size.toString())

    }


}

