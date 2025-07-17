package com.example.midletatlas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar el Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Mantener la funcionalidad del botón "COMENZAR"
        val comenzar = findViewById<Button>(R.id.button_comenzar)
        comenzar.setOnClickListener {
            val intent = Intent(this, MapamundiActivity::class.java)
            startActivity(intent)
        }
    }

    // Inflar el menú del Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Manejar clics en las opciones del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.instrucciones -> {
                mostrarDialogo(
                    "Instrucciones", "Esta aplicación es la 2ª practica de DAM"
                            + " \n consiste en seleccionar un continente ver curiosidades \n" +
                            "de este y elegir un pais , veremos bandera y localización \n, gracias y un saludo  "
                )
                true
            }

            R.id.creditos -> {
                mostrarDialogo("Créditos", "Desarrollado por Felipe Castillo Rodriguez.")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    // Método para mostrar un cuadro de diálogo con un mensaje
    private fun mostrarDialogo(titulo: String, mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
