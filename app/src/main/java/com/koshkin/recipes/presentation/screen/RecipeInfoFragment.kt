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
import com.bumptech.glide.Glide
import com.koshkin.recipes.R
import com.koshkin.recipes.databinding.FragmentRecipeInfoBinding
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.presentation.MAIN
import com.koshkin.recipes.presentation.RecipesAdapter
import com.koshkin.recipes.presentation.RecipesViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private const val ARG_PARAM = "recipe"


class RecipeInfoFragment : Fragment() {
    private var recipe: String? = null

    private  var recipeRead:Results? = null

    private var adapter:ArrayAdapter<String>? =null

    private lateinit var binding: FragmentRecipeInfoBinding

    private var changeConstraint: ConstraintSet? =null



    private val recipesViewModel: RecipesViewModel by activityViewModels()

    // var info= List<Results>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipe = it.getString(ARG_PARAM)
        }
       val json = Json
        recipeRead = recipe?.let { json.decodeFromString(it) }!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRecipeInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvButton.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("recipe",recipe)
//            bundle.putInt("Id",recipeRead?.id!!)
//            Log.i("RIF",recipeRead?.id!!.toString())
            MAIN.navController.navigate(R.id.action_recipeInfoFragment_to_recipeDetailsFragment,bundle)
        }


        recipeRead!!.thumbnailUrl.let { imageUrl ->
            Glide.with(requireContext())
                .load(imageUrl)
                .into(binding.ivRecipeInfo)
        }

        binding.infoName.text =recipeRead?.name

        if(recipeRead?.aspectRatio.toString().equals("9:16")){
            binding.infoDescription169.visibility = View.VISIBLE
            binding.infoDescription169.text = recipeRead?.description
        } else if(recipeRead?.aspectRatio!=null){
            binding.infoDescription.visibility = View.VISIBLE
            binding.infoDescription.text = recipeRead?.description
        }


    }


}