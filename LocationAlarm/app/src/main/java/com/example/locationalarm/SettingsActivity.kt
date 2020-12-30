package com.example.locationalarm

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
//    val sharedPref = this?.getSharedPreferences(getString(R.string.PREFS_NAME), Context.MODE_PRIVATE) ?: return
//    with(sharedPref.edit()) {
//        putString("userLat", Lat?.text.toString())
//        putString("userLng", Lng?.text.toString())
//        commit()
//    }

//    val sharedPref : SharedPreferences = this?.getSharedPreferences(getString(R.string.PREFS_NAME), Context.MODE_PRIVATE) ?:
//    return with (sharedPref.edit()) {
//        putString("userLat", Lat?.text.toString())
//        putString("userLng", Lng?.text.toString())
//        apply()
//    }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
    }

    fun onClickOkButton(view: View) {
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_LONG).show()
        val latText : EditText? = findViewById<EditText>(R.id.latText)
        val lngText : EditText? = findViewById<EditText>(R.id.lngText)

        val sharedPref = this?.getSharedPreferences(getString(R.string.PREFS_NAME), Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putString("userLat", latText!!.text.toString())
                putString("userLng", lngText!!.text.toString())
                apply()
            }
    }
}