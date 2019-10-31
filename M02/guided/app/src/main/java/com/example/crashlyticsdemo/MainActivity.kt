package com.example.crashlyticsdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        crashButton.setOnClickListener {
            dropBreadCrubbs("MainActivity","crashButtonListener", 0L, "Button click force crash!")
            throw RuntimeException("Simulated crash: This is a forced crash!")
        }
        buttonNextActivity.setOnClickListener {
            val intent = Intent(this, OtherActivity::class.java)
            startActivity(intent)
        }
    }
}
