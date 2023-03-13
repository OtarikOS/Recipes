package com.koshkin.recipes.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.koshkin.recipes.R
import com.koshkin.recipes.domain.entity.Components

class IngredientAdapter(
    private val context: Context,
private val data:List<Components>) : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: IngredientAdapter.ViewHolder, position: Int) {
        holder.tvInfoData.text = data[position].rawText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.ingredient_item,parent,false)
        )
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvInfoData: TextView = view.findViewById(R.id.tvIngredient)


    }
}