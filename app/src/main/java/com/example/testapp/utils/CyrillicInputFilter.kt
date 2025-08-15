package com.example.testapp.utils

import android.text.InputFilter
import android.text.Spanned

class CyrillicInputFilter : InputFilter {
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        for (i in start until end) {
            val character = source[i]
            if (character.code in 0x0400..0x04FF || character.toInt() in 0x0500..0x052F) {
                return ""
            }
        }
        return null
    }
}