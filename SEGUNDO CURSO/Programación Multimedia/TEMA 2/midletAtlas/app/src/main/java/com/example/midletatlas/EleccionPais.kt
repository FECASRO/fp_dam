package com.example.midletatlas

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EleccionPais : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eleccion_pais)

        val atras = findViewById<Button>(R.id.buttonBackFinal)

        // Recuperar referencias de los elementos gráficos
        val banderaImageView = findViewById<ImageView>(R.id.bandera)
        val nombrePaisTextView = findViewById<TextView>(R.id.nombrepais)
        val imagenExtraImageView = findViewById<ImageView>(R.id.imageView4)

        // Obtener el país seleccionado de la actividad anterior
        val paisSeleccionado = intent.getStringExtra("PAIS_SELECCIONADO")

        // Configurar los elementos según el país seleccionado
        when (paisSeleccionado) {
            "China" -> {
                banderaImageView.setImageResource(R.drawable.banderachina)
                nombrePaisTextView.text = "China"
                imagenExtraImageView.setImageResource(R.drawable.chn)
            }
            "India" -> {
                banderaImageView.setImageResource(R.drawable.bandeindia)
                nombrePaisTextView.text = "India"
                imagenExtraImageView.setImageResource(R.drawable.ind)
            }
            "Japón" -> {
                banderaImageView.setImageResource(R.drawable.bandejapon)
                nombrePaisTextView.text = "Japón"
                imagenExtraImageView.setImageResource(R.drawable.jpn)
            }
            "España" -> {
                banderaImageView.setImageResource(R.drawable.bandespania)
                nombrePaisTextView.text = "España"
                imagenExtraImageView.setImageResource(R.drawable.spn)
            }
            "Alemania" -> {
                banderaImageView.setImageResource(R.drawable.bandealemania)
                nombrePaisTextView.text = "Alemania"
                imagenExtraImageView.setImageResource(R.drawable.alm)
            }
            "Italia" -> {
                banderaImageView.setImageResource(R.drawable.bandeitalia)
                nombrePaisTextView.text = "Italia"
                imagenExtraImageView.setImageResource(R.drawable.ita)
            }
            "Nigeria" -> {
                banderaImageView.setImageResource(R.drawable.bandenigeria)
                nombrePaisTextView.text = "Nigeria"
                imagenExtraImageView.setImageResource(R.drawable.ngr)
            }
            "Sudafrica" -> {
                banderaImageView.setImageResource(R.drawable.bandesudafrica)
                nombrePaisTextView.text = "Sudafrica"
                imagenExtraImageView.setImageResource(R.drawable.sua)
            }
            "Egipto" -> {
                banderaImageView.setImageResource(R.drawable.bandeegipto)
                nombrePaisTextView.text = "Egipto"
                imagenExtraImageView.setImageResource(R.drawable.egp)
            }
            "EE.UU." -> {
                banderaImageView.setImageResource(R.drawable.eeuu)
                nombrePaisTextView.text = "EE.UU"
                imagenExtraImageView.setImageResource(R.drawable.ame)
            }
            "Mexico" -> {
                banderaImageView.setImageResource(R.drawable.bandemexico)
                nombrePaisTextView.text = "Mexico"
                imagenExtraImageView.setImageResource(R.drawable.mxo)
            }
            "Brasil" -> {
                banderaImageView.setImageResource(R.drawable.bandebrasil)
                nombrePaisTextView.text = "Brasil"
                imagenExtraImageView.setImageResource(R.drawable.brs)
            }
            "Australia" -> {
                banderaImageView.setImageResource(R.drawable.bandeaustralia)
                nombrePaisTextView.text = "Australia"
                imagenExtraImageView.setImageResource(R.drawable.aus)
            }
            "Nueva Zelanda" -> {
                banderaImageView.setImageResource(R.drawable.bandenzelanda)
                nombrePaisTextView.text = "Nueva Zelanda"
                imagenExtraImageView.setImageResource(R.drawable.nzd)
            }
            "Fiyi" -> {
                banderaImageView.setImageResource(R.drawable.bandefiyi)
                nombrePaisTextView.text = "Fiyi"
                imagenExtraImageView.setImageResource(R.drawable.fiy)
            }
            else -> {
                // Por defecto, si no hay datos o país no reconocido
                banderaImageView.setImageResource(R.drawable.default_bandera)
                nombrePaisTextView.text = "País desconocido"
                imagenExtraImageView.setImageResource(R.drawable.default_image)
            }
        }

        atras.setOnClickListener {
            finish()
        }
    }
}