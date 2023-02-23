package com.koshkin.recipes.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.koshkin.recipes.R
import com.koshkin.recipes.domain.entity.Results

class RecipesAdapter(
    private val context: Context,
    private val listener: ActionClickListener
): RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    private val recipes: ArrayList<Results> = arrayListOf() //TODO сделать "энтити" для презентэйшн и переписать recipes

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_recipe,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        recipes[position].also { recipe ->
            recipe.thumbnailUrl.let { imageUrl ->
                Glide.with(context)
                    .load(imageUrl)
                    .into(holder.ivRecipeCover)
                holder.tvRecipeID.text = ""
                holder.tvRecipeName.text= ""
            } ?: kotlin.run {
                Glide.with(context)
                    .load(R.drawable.ic_launcher_background)
                    .into(holder.ivRecipeCover)
                holder.tvRecipeID.text = recipe.id.toString()
                holder.tvRecipeName.text =recipe.name
            }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvRecipeName: TextView = view.findViewById(R.id.tvRecipeName)
        val tvRecipeID: TextView = view.findViewById(R.id.tvRecipeID)
        val ivRecipeCover: ImageView = view.findViewById(R.id.ivRecipeCover)
    }

    fun submitUpdate(update: List<Results>) {    //TODO сделать "энтити" для презентэйшн и переписать
        val callback = RecipesDiffCallback(recipes, update)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)

        recipes.clear()
        recipes.addAll(update)
        diffResult.dispatchUpdatesTo(this)
    }


    class RecipesDiffCallback(
        private val oldRecipes: List<Results>,             //TODO сделать "энтити" для презентэйшн и переписать
        private val newRecipes: List<Results>           //TODO сделать "энтити" для презентэйшн и переписать
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldRecipes.size
        }

        override fun getNewListSize(): Int {
            return newRecipes.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldRecipes[oldItemPosition].id == newRecipes[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldRecipes[oldItemPosition].id == newRecipes[newItemPosition].id
        }
    }

    interface ActionClickListener {
        fun moreInfo(recipeID: Int)
    }

}


