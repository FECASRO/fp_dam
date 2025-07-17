

package com.example.midletatlas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class DetalleContinenteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_continente)

        val imageView = findViewById<ImageView>(R.id.imageViewActualizable)
        val textView = findViewById<TextView>(R.id.textViewActualizable)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupActualizable)
        val continueButton = findViewById<Button>(R.id.buttonContinuarACity)
        val backButton = findViewById<Button>(R.id.buttonBack2)

        // Obtener el continente desde el intent
        val continent = intent.getStringExtra("CONTINENT") ?: ""

        // Actualizar UI según el continente
        when (continent) {
            "Asia" -> {
                imageView.setImageResource(R.drawable.asia)
                textView.text = "Con 44.58 millones de km² y una población de ~4,7 mil millones (2023) y 49 países, Asia es el continente más grande y poblado, cuna de antiguas civilizaciones como la china y la india."
                setCountryOptions(radioGroup, "China", "India", "Japón")
            }
            "Europa" -> {
                imageView.setImageResource(R.drawable.europa)
                textView.text = "Con 10.18 millones de km² y una población de ~750 millones (2023) y 44 países, Europa es el epicentro histórico de la cultura occidental y hogar de grandes innovaciones."
                setCountryOptions(radioGroup, "España", "Alemania", "Italia")
            }
            "África" -> {
                imageView.setImageResource(R.drawable.africa)
                textView.text = "Con 30.37 millones de km² y una población de ~1,4 mil millones (2023) y 54 países, África destaca por su diversidad cultural y riqueza natural, siendo la cuna de la humanidad."
                setCountryOptions(radioGroup, "Egipto", "Sudafrica", "Nigeria")
            }
            "América" -> {
                imageView.setImageResource(R.drawable.america)
                textView.text = "Con 42.55 millones de km² y una población de ~1 mil millones (2023) y 35 países, América abarca desde el Ártico hasta la Antártida, con paisajes y culturas fascinantes."
                setCountryOptions(radioGroup, "EE.UU.", "Brasil", "Mexico")
            }
            "Oceanía" -> {
                imageView.setImageResource(R.drawable.oceania)
                textView.text = "Con 8.52 millones de km² y una población de ~43 millones (2023) y 14 países, Oceanía se distingue por su biodiversidad y su riqueza en culturas indígenas."
                setCountryOptions(radioGroup, "Australia", "Nueva Zelanda", "Fiyi")
            }
            else -> {
                Toast.makeText(this, "Continente no reconocido", Toast.LENGTH_SHORT).show()
            }
        }

        // Configuración del botón "Regresar"
        backButton.setOnClickListener {
            finish() // Regresa a la actividad anterior
        }

        // Configuración del botón "Continuar"
        continueButton.setOnClickListener {
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            if (selectedRadioButtonId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                val selectedCountry = selectedRadioButton.text.toString()

                // Enviar el país seleccionado a la siguiente actividad
                val intent = Intent(this, EleccionPais::class.java)
                intent.putExtra("PAIS_SELECCIONADO", selectedCountry)
                startActivity(intent)
            } else {
                // Mostrar un mensaje si no se seleccionó ningún país
                Toast.makeText(this, "Por favor selecciona un país.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setCountryOptions(group: RadioGroup, country1: String, country2: String, country3: String) {
        val button1 = group.findViewById<RadioButton>(R.id.radioButtonActuali1)
        val button2 = group.findViewById<RadioButton>(R.id.radioButtonActuali2)
        val button3 = group.findViewById<RadioButton>(R.id.radioButtonActuali3)

        button1?.text = country1
        button2?.text = country2
        button3?.text = country3
    }
}
