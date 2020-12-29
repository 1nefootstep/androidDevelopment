package com.example.tictactoe

import android.view.View
import com.google.android.material.snackbar.Snackbar

class HelloKotlin (message: String){
    constructor(): this("Hello Kotlin!") //if provided with no args

    private val msg = message

    fun displayMessage(view: View) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }
}