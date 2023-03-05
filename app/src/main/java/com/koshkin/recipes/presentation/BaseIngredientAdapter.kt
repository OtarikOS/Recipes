package com.koshkin.recipes.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
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
        val constraintRoot:RecyclerView = view.findViewById(R.id.constraint_root)
        val recyclerView:RecyclerView = view.findViewById(R.id.recycler_ingredient_base)
        val ivFB:ImageView=view.findViewById(R.id.favorite_border)
        val scroll:ScrollView = view.findViewById(R.id.scroll)
        val const1:ConstraintLayout = view.findViewById(R.id.constraint_recipe_info)
        val image:ImageView =view.findViewById(R.id.ivRecipeInfo)
        val description:TextView = view.findViewById(R.id.info_description)
        val ivF:ImageView =view.findViewById(R.id.favorite)
        val tvName:TextView = view.findViewById(R.id.info_name)
        val description_16:TextView = view.findViewById(R.id.info_description_16_9)
        val tvComp:TextView =view.findViewById(R.id.info_components)
        val recInfoList:RecyclerView =view.findViewById(R.id.info_list)
        val tvButton:TextView = view.findViewById(R.id.tvButton)
    }

    override fun getItemCount(): Int {
        var size:Int
        if(data.size>1)
            size=data.size-1
        else size= data.size
        return size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = data[position]
        val set =ConstraintSet()
//        val childLayoutManager = LinearLayoutManager(             holder.recyclerView.context, RecyclerView.VERTICAL, false)
//        childLayoutManager.initialPrefetchItemCount = 1
        holder.ivFB.visibility = View.GONE
     //   holder.scroll.visibility =View.GONE
        holder.image.visibility =View.GONE
      //  holder.const1.visibility =View.GONE
      //  holder.constraintRoot.visibility = View.GONE
        holder.description.visibility =View.GONE
        holder.ivF.visibility =View.GONE
        holder.tvName.visibility =View.GONE
        holder.description_16.visibility =View.GONE
        holder.tvComp.visibility =View.GONE
        holder.recInfoList.visibility =View.GONE
        holder.tvButton.visibility =View.GONE

        set.clear(R.id.recycler_ingredient_base,ConstraintSet.TOP)
        set.connect(R.id.recycler_ingredient_base,ConstraintSet.TOP,R.id.scroll,ConstraintSet.TOP)

        holder.recyclerView.apply {
          //  layoutManager = childLayoutManager

            adapter = IngredientAdapter(context,data[position+1].components)
         //   setRecycledViewPool(viewPool)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.fragment_recipe_info,parent,false)
        )
    }
}