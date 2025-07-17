package com.example.midletmensajero

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EliminarClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_cliente)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEliminarClientes)
        val btnAtras = findViewById<Button>(R.id.btnAtras)

        // Recuperar todos los clientes desde SharedPreferences
        val sharedPreferences = getSharedPreferences("ClientesData", Context.MODE_PRIVATE)
        val allEntries = sharedPreferences.all

        val clientes = mutableListOf<String>()
        val keys = mutableListOf<String>()

        // Recuperar claves y datos
        for ((key, value) in allEntries) {
            if (value is String) { // Asegurarnos de que sean cadenas
                keys.add(key)
                clientes.add(value)
            }
        }

        // Configurar el RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ClienteAdapter(
            this,
            clientes,
            onDelete = { position ->
                // Eliminar cliente de SharedPreferences
                val clienteKey = keys[position]
                val editor = sharedPreferences.edit()
                editor.remove(clienteKey)
                editor.apply()

                // Eliminar de las listas locales
                clientes.removeAt(position)
                keys.removeAt(position)
                recyclerView.adapter?.notifyItemRemoved(position)

                // Confirmación visual
                Toast.makeText(this, "Cliente eliminado permanentemente", Toast.LENGTH_SHORT).show()
            },
            mostrarEliminar = true // Asegurarse de que el botón "Eliminar" esté visible
        )

        // Botón ATRÁS
        btnAtras.setOnClickListener {
            finish()
        }
    }
}
