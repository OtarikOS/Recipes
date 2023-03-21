package com.koshkin.recipes.domain.transformation

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import java.lang.StringBuilder

class EditorRecipe(editText: EditText, stringa: String) {
    private val str: StringBuilder = StringBuilder().append(stringa)
    private val editTextThis:EditText = editText

    fun edition(stringa: String){
        editTextThis.setText(stringa)
        editTextThis.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(editTextThis.text.toString() != stringa){
                editTextThis.setBackgroundColor(Color.MAGENTA)
                Log.i("Edit","true")}
                else{
                    editTextThis.setBackgroundColor(Color.WHITE)
                Log.i("Edit","false")}
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    operator fun invoke(editText: EditText, stringa: String): EditorRecipe{
        return EditorRecipe(editText, stringa)
    }
}