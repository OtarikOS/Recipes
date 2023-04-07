package com.koshkin.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.koshkin.recipes.presentation.MAIN
import com.koshkin.recipes.presentation.RecipesViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var navController: NavController

    private val viewModel: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.gradient))
        supportActionBar?.setTitle(Html.fromHtml("<font color='#ff0000'>ActionBarTitle </font>"))




        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        MAIN=this
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.queryHint = "kuofg"
        // here you get error but don't worry
        searchView?.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
      //  TODO("Not yet implemented")
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
      //  TODO("Not yet implemented")
        return true
    }
}