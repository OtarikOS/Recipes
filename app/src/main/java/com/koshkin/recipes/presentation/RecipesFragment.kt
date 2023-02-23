package com.koshkin.recipes.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.koshkin.recipes.App
import com.koshkin.recipes.LayoutUtils
import com.koshkin.recipes.R
import com.koshkin.recipes.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {
    private lateinit var recipesAdapter: RecipesAdapter

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val recipesViewModel: RecipesViewModel by viewModels {
        Log.i("KEK", requireActivity().application.toString())
        RecipesViewModel.RecipesViewModelFactory(
            ((requireActivity().application) as App).getRemoteRecipes,
            ((requireActivity().application) as App).getRecipeInfo,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesAdapter = RecipesAdapter(requireContext(), object : RecipesAdapter.ActionClickListener {
            override fun moreInfo(recipeID: Int) {
                TODO("Not yet implemented")
            }

        })

        recipesViewModel.getRecipes(null,null)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_recipes, container, false)
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipesViewModel.recipes.observe(viewLifecycleOwner, {
            recipesAdapter.submitUpdate(it)
        })

        recipesViewModel.dataLoading.observe(viewLifecycleOwner, { loading ->
            when (loading) {
                true -> LayoutUtils.crossFade( binding.pbLoading, binding.rvRecipe)
                false -> LayoutUtils.crossFade(binding.rvRecipe, binding.pbLoading)
            }
        })

        binding.rvRecipe.apply {
            layoutManager =
                GridLayoutManager(requireContext(), COLUMNS_COUNT)
            adapter = recipesAdapter
        }

        recipesViewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(
                requireContext(),
  //              getString(R.string.an_error_has_occurred, it),
                "Не могу ни хера",
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    companion object {
        const val COLUMNS_COUNT = 2
    }
}