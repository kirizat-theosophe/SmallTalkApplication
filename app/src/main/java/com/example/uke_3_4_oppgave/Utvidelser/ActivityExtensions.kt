package com.example.uke_3_4_oppgave.Utvidelser

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard(){
    currentFocus?.let {view ->
        val imm = getSystemService(Context. INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}