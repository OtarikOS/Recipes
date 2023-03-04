package com.koshkin.recipes.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.koshkin.recipes.R

 class InfoAdapter(
    private val context: Context,
    private val data: ArrayList<String>
) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvPosition.text =  (position+1).toString()
        holder.tvInfoData.text = data[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_component,parent,false)
        )
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvInfoData:TextView = view.findViewById(R.id.list_content)
        val tvPosition: TextView = view.findViewById(R.id.list_position)
    }
}