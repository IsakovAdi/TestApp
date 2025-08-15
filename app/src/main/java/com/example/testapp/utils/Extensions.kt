package com.example.testapp.utils

import android.text.InputFilter
import android.util.Patterns
import android.widget.EditText

fun CharSequence.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun EditText.initEmailInputValidation(){
    val filterArray = arrayOfNulls<InputFilter>(1)
    filterArray[0] = CyrillicInputFilter()
    this.filters = filterArray
}

