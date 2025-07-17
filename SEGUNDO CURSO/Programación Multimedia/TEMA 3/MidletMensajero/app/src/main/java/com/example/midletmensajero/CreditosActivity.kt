package com.example.midletmensajero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CreditosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creditos)

        findViewById<Button>(R.id.atrasCreditos).setOnClickListener{

            finish()

        }
    }
}