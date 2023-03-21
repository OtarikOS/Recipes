package com.koshkin.recipes.presentation.screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.koshkin.recipes.R
import com.koshkin.recipes.databinding.FragmentRecipeInfoBinding
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.domain.transformation.EditorRecipe
import com.koshkin.recipes.presentation.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


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

    private  var editorRecipe: EditorRecipe? = null





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

       // binding.infoName.setText(recipeRead?.name)
        editorRecipe = EditorRecipe(binding.infoName,recipeRead?.name.toString())
        editorRecipe!!.edition(recipeRead?.name.toString())

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

