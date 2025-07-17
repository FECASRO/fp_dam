package com.example.midletmensajero

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AnadirClienteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_cliente)

        val etNombre = findViewById<EditText>(R.id.etNombreCliente)
        val etTelefono = findViewById<EditText>(R.id.etTelefonoCliente)
        val btnGuardar = findViewById<Button>(R.id.btnAñadirCliente)
        val btnAtras = findViewById<Button>(R.id.atrasAnadirCliente)

        // Guardar datos al pulsar el botón
        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val telefono = etTelefono.text.toString()

            if (nombre.isNotBlank() && telefono.isNotBlank()) {
                guardarCliente(nombre, telefono)
                Toast.makeText(this, "Cliente añadido con éxito", Toast.LENGTH_SHORT).show()
                finish() // Vuelve a la pantalla anterior
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Volver atrás
        btnAtras.setOnClickListener {
            finish()
        }
    }

    private fun guardarCliente(nombre: String, telefono: String) {
        // Guardar datos en SharedPreferences
        val sharedPreferences = getSharedPreferences("ClientesData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Crear una clave única para cada cliente
        val clienteKey = "Cliente_${System.currentTimeMillis()}"

        // Almacenar los datos en formato JSON-like
        editor.putString(clienteKey, "$nombre:$telefono")
        editor.apply()
    }
}
