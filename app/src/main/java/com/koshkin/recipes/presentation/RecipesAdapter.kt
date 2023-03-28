package com.koshkin.recipes.presentation

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.koshkin.recipes.R
import com.koshkin.recipes.presentation.sent.RecipesForFragmentWithStatus
import com.koshkin.recipes.presentation.sent.SentRecipeStatus
import kotlinx.serialization.json.Json

class RecipesAdapter(
    private val context: Context,
    private val listener: ActionClickListener
): RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {



    private val recipes: ArrayList<RecipesForFragmentWithStatus> = arrayListOf() //TODO сделать "энтити" для презентэйшн и переписать recipes


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
        Log.i("R_ADAP",position.toString())
     //   recipes.addAll(recipes)
        if (position ==recipes.size-10)
            listener.addRecipes(31,20,null,null)
        recipes[position].also { recipe ->
            recipe.imageUrl?.let { imageUrl ->
                Glide.with(context)
                    .load(imageUrl)
                    .into(holder.ivRecipeCover)
                holder.tvRecipeID.text = recipe.id.toString()
                holder.tvRecipeName.text= recipe.name
                if (recipe.status == SentRecipeStatus.SENT){
                holder.ivSent.visibility = View.VISIBLE
                }
            } ?: kotlin.run {
                Glide.with(context)
                    .load(R.drawable.ic_launcher_background)
                    .into(holder.ivRecipeCover)
                holder.tvRecipeID.text = recipe.id.toString()
                holder.tvRecipeName.text =recipe.name
                if (recipe.status == SentRecipeStatus.SENT){
                    holder.ivSent.visibility = View.VISIBLE
                }
            }
        }

        holder.ivRecipeCover.setOnClickListener{
       //     val json=Json
          //  if(recipes[position].nutrition?.calories ==null){
                listener.moreInfo(recipes[position].id.toString())
//            }else {
//                val str = json.encodeToString(recipes[position])
//                Log.i("RADAP", str)
//                listener.moreInfo(str)
//            }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvRecipeName: TextView = view.findViewById(R.id.tvRecipeName)
        val tvRecipeID: TextView = view.findViewById(R.id.tvRecipeID)
        val ivRecipeCover: ImageView = view.findViewById(R.id.ivRecipeCover)
        val ivSent: ImageView = view.findViewById(R.id.ivSend)
    }

    fun submitUpdate(update: List<RecipesForFragmentWithStatus>) {    //TODO сделать "энтити" для презентэйшн и переписать
        val callback = RecipesDiffCallback(recipes, update)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)

        recipes.clear()
        recipes.addAll(update)
        diffResult.dispatchUpdatesTo(this)
    }


    class RecipesDiffCallback(
        private val oldRecipes: List<RecipesForFragmentWithStatus>,             //TODO сделать "энтити" для презентэйшн и переписать
        private val newRecipes: List<RecipesForFragmentWithStatus>           //TODO сделать "энтити" для презентэйшн и переписать
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
            return oldRecipes[oldItemPosition].status == newRecipes[newItemPosition].status
        }
    }

    interface ActionClickListener {
         fun moreInfo(str:String)

        fun addRecipes(from :Int,size : Int,tag: String?,ingredient: String?)
    }

}


