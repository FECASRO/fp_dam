package com.example.midletmensajero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.salir).setOnClickListener {

            finish()
        }
        findViewById<Button>(R.id.clientlist).setOnClickListener {

            startActivity(Intent(this,ClientesActivity::class.java))

        }
        findViewById<Button>(R.id.mensajes).setOnClickListener {

            startActivity(Intent(this,PlantillaActivity::class.java))

        }
        findViewById<Button>(R.id.enviaMSG).setOnClickListener {

            startActivity(Intent(this,EnviarMSGActivity::class.java))

        }
        findViewById<Button>(R.id.creditos).setOnClickListener {

            startActivity(Intent(this,CreditosActivity::class.java))

        }
        findViewById<Button>(R.id.instrucciones).setOnClickListener {

            startActivity(Intent(this,InstruccionesActivity::class.java))

        }
    }
}