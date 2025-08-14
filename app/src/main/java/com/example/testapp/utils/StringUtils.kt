package com.example.testapp.utils

import android.util.Patterns

fun CharSequence.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()