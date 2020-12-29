package com.example.locationalarm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
    }

    fun onClickOkButton(view: View) {
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_LONG).show()
    }
}