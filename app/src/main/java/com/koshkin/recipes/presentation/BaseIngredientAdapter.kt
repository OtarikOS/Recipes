package com.koshkin.recipes.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.koshkin.recipes.R
import com.koshkin.recipes.domain.entity.Sections

class BaseIngredientAdapter(
    private val context: Context,
    private val data:List<Sections>
): RecyclerView.Adapter<BaseIngredientAdapter.ViewHolder>() {

   // private val viewPool = RecyclerView.RecycledViewPool()

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val recyclerView:RecyclerView = view.findViewById(R.id.recycler_ingredient_base)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = data[position]
//        val childLayoutManager = LinearLayoutManager(             holder.recyclerView.context, RecyclerView.VERTICAL, false)
//        childLayoutManager.initialPrefetchItemCount = 1
        holder.recyclerView.apply {
          //  layoutManager = childLayoutManager

            adapter = IngredientAdapter(context,data[position].components)
         //   setRecycledViewPool(viewPool)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.ingrediens_layout_item,parent,false)
        )
    }
}