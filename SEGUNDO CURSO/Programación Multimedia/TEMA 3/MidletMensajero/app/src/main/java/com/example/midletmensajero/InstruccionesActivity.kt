package com.example.midletmensajero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InstruccionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instrucciones)

        findViewById<Button>(R.id.atrasInstrucciones).setOnClickListener {
            finish()
        }
    }
}