package com.koshkin.recipes.domain.transformation

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import java.lang.StringBuilder

class EditorRecipe(editText: EditText, var stringa: String) {
    private val str: StringBuilder = StringBuilder().append(stringa)
    private val editTextThis:EditText = editText

    var result:String = stringa


    fun edition( stringa: String){
        editTextThis.setText(stringa)

        editTextThis.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(editTextThis.text.toString() != stringa){
                editTextThis.setBackgroundColor(Color.MAGENTA)
                Log.i("Edit","true")
                    editTextThis.setOnKeyListener(object : View.OnKeyListener {
                        override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                            // if the event is a key down event on the enter button
                            if (event.action == KeyEvent.ACTION_DOWN &&
                                keyCode == KeyEvent.KEYCODE_ENTER
                            ) {
                                editTextThis.setBackgroundColor(Color.WHITE)
                                result = editTextThis.text.toString()

                               // return true
                            }
                            return false
                        }
                    })
                }
                else{
                    editTextThis.setBackgroundColor(Color.WHITE)
                Log.i("Edit","false")}
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        this.stringa =result
    }

    operator fun invoke(editText: EditText, stringa: String): EditorRecipe{
        return EditorRecipe(editText, stringa)
    }
}