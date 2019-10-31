package com.example.analyticsdemoAY

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        FirebaseAnalytics.getInstance(this).setCurrentScreen(this, "MainActivity","Test2")

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1")
        bundle.putString(FirebaseAnalytics.Param.QUANTITY, "42")
        FirebaseAnalytics.getInstance(this).logEvent("main_screen_view", bundle)

        buttonFire.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1")
            bundle.putString(FirebaseAnalytics.Param.QUANTITY, "42")
            FirebaseAnalytics.getInstance(this).logEvent("button_fire_click", bundle)
        }
    }
}
