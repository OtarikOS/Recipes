package com.koshkin.recipes.presentation.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.koshkin.recipes.R
import com.koshkin.recipes.databinding.FragmentRecipeInfoBinding
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.presentation.RecipesAdapter
import com.koshkin.recipes.presentation.RecipesViewModel


class RecipeInfoFragment : Fragment() {
    private lateinit var binding: FragmentRecipeInfoBinding

    private val recipesViewModel: RecipesViewModel by activityViewModels()

    // var info= List<Results>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

      //  binding.tv.text = info[1].name
    }
}