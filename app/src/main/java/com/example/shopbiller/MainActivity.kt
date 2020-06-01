package com.example.shopbiller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        Handler().postDelayed({

            val i = Intent(this,Homepage::class.java)
            startActivity(i)
            finish()

        },1000)
//        splash.setOnClickListener {
//
//            var intent = Intent(this,Homepage::class.java)
//
//            startActivity(intent)
//        }
    }
}
