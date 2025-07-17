package com.example.midletatlas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

class MapamundiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapamundi)

        val atras1 = findViewById<Button>(R.id.buttonBack1)
        val continue1 = findViewById<Button>(R.id.buttonContdesdeContinen)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupcontinentes)

        atras1.setOnClickListener {
            finish()
        }

        continue1.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Debes seleccionar un continente", Toast.LENGTH_SHORT).show()
            } else {
                val selectedContinent = when (selectedId) {
                    R.id.radioButtonASI -> "Asia"
                    R.id.radioButtonEUR -> "Europa"
                    R.id.radioButtonAFR -> "África"
                    R.id.radioButtonAME -> "América"
                    R.id.radioButtonOCE -> "Oceanía"
                    else -> ""
                }
                // Pasar el continente a la siguiente actividad
                val intent = Intent(this, DetalleContinenteActivity::class.java)
                intent.putExtra("CONTINENT", selectedContinent)
                startActivity(intent)
            }
        }
    }
}