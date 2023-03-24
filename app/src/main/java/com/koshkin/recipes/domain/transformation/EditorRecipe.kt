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


    fun edition():String{
        var keyCodex:Boolean =false
        editTextThis.setText(stringa)

//       // while (!keyCodex) {
//            if (!editTextThis.text.equals(stringa)) {
//                editTextThis.setBackgroundColor(Color.MAGENTA)
//            } else {
//                editTextThis.setBackgroundColor(Color.WHITE)
//            }
            editTextThis.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    // if the event is a key down event on the enter button
                    if (//event.action == KeyEvent.ACTION_DOWN&&

                        keyCode == KeyEvent.KEYCODE_ENTER
                    ) {
                        editTextThis.setBackgroundColor(Color.WHITE)
                        stringa = editTextThis.text.toString()
                        keyCodex = true
                        Log.i("EDIT_stringa", stringa)
                        return keyCodex
                    } else if (event.action == KeyEvent.ACTION_DOWN) {
                        editTextThis.setBackgroundColor(Color.MAGENTA)
                        Log.i("EDIT_false", result)
                        keyCodex = false
                        return keyCodex
                    }
                    return keyCodex
                }

            })
//       // }


//        editTextThis.addTextChangedListener(object :TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//stringa = editTextThis.text.toString()
//
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//
//        })
        return stringa
    }

    operator fun invoke(editText: EditText, stringa: String): EditorRecipe{
        return EditorRecipe(editText, stringa)
    }
}