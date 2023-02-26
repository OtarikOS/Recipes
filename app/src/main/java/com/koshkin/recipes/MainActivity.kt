package com.koshkin.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.koshkin.recipes.presentation.RecipesViewModel

class MainActivity : AppCompatActivity() {
    private val navController by lazy {
        val navController= Navigation.findNavController(this,R.id.nav_host_fragment)
    }

    private val viewModel: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}