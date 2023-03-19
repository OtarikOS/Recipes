package com.koshkin.recipes.presentation.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.koshkin.recipes.App
import com.koshkin.recipes.R
import com.koshkin.recipes.data.repositories.LocalDataSourceImp
import com.koshkin.recipes.data.repositories.RecipesRepositoryImpl
import com.koshkin.recipes.databinding.FragmentRecipesBinding
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.domain.repositories.RecipesRepository
import com.koshkin.recipes.domain.usecases.GetRecipeInfo
import com.koshkin.recipes.domain.usecases.SaveAllRecipes
import com.koshkin.recipes.presentation.MAIN
import com.koshkin.recipes.presentation.RecipesAdapter
import com.koshkin.recipes.presentation.RecipesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RecipesFragment : Fragment() {
    private lateinit var recipesAdapter: RecipesAdapter

    private var statePosition: Int = 0
    private var sizeRequest: Int = 30
    var recipe: Results? = null

    private var allowRequest: Boolean = true

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val recipesViewModel: RecipesViewModel by activityViewModels() {
        Log.i("KEK", requireActivity().application.toString())
        RecipesViewModel.RecipesViewModelFactory(
            ((requireActivity().application) as App).getRemoteRecipes,
            ((requireActivity().application) as App).getRecipeInfo,
            ((requireActivity().application) as App).postRecipe,
            ((requireActivity().application) as App).getKey,
            ((requireActivity().application) as App).getTranslate,
            ((requireActivity().application) as App).deleteAll,
            ((requireActivity().application) as App).getSavedRecipes,
            ((requireActivity().application) as App).saveAllRecipes
        )
    }

    override fun onPause() {
        super.onPause()
        CoroutineScope(Dispatchers.IO).launch {
            val job = launch {
                recipesViewModel.deleteAll()
                recipesViewModel.saveAllRecipes(recipesViewModel.recipeDb)
            }
            job.join()
            Log.i("RF_onPause", recipesViewModel.recipeDb.toString())
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesAdapter =
            RecipesAdapter(requireContext(), object : RecipesAdapter.ActionClickListener {
                override fun moreInfo(string: String) {
                    val bundle = Bundle()
                    var str: String
                    var s: String
                    Log.i("REC_FR", string)  //TODO Log
                    viewLifecycleOwner.lifecycleScope.launch {
                        val job = launch {
                            if (string.length < 5) {
                                Log.i("RF_COROUT", "async")
                                recipesViewModel.getInfoRecipe(string.toInt())
                                val json = Json
                                str = json.encodeToString(recipesViewModel.oneRecipes)
                                bundle.putString("recipe", str)
                            } else bundle.putString("recipe", string)
                        }
                        job.join()
                        MAIN.navController.navigate(
                            R.id.action_forecastFragment_to_recipeInfoFragment,
                            bundle
                        )
                    }


                }


                override fun addRecipes(from: Int, size: Int, tag: String?, ingredient: String?) {
                    //      if(allowRequest) {    проверка нужна??? как и в recipeviewmodel                TODO проверить
                    //    allowRequest =
                    recipesViewModel.getRecipes(from + statePosition, size, tag, ingredient)
                    statePosition += 20
                    //    }
                }

            })
        CoroutineScope(Dispatchers.IO).launch {
            val job = launch {
                recipesViewModel.getSavedRecipes()
            }
            job.join()
            Log.i("RF_getFromDb", recipesViewModel.remoteRecipes.toString())

            if (recipesViewModel.remoteRecipes.isEmpty())
            recipesViewModel.getRecipes(statePosition, sizeRequest, null, null)
        }
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
                true -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                false -> {
                    binding.pbLoading.visibility = View.GONE
                }
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