package com.koshkin.recipes.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
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

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.tvPosition.text =  (position+1).toString()
        holder.tvInfoData.text = data[position]
        holder.tvInfoData.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            data[position] = holder.tvInfoData.text.toString()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
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