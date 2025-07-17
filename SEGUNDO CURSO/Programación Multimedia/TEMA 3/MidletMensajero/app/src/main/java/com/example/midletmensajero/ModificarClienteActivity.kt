package com.example.midletmensajero

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ModificarClienteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_cliente)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewClientesModificar)
        val etNombre = findViewById<EditText>(R.id.etNombreModificar)
        val etTelefono = findViewById<EditText>(R.id.etTelefonoModificar)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnAtras = findViewById<Button>(R.id.btnAtrasModificar)

        // Recuperar todos los clientes desde SharedPreferences
        val sharedPreferences = getSharedPreferences("ClientesData", Context.MODE_PRIVATE)
        val allKeys = sharedPreferences.all.keys

        val clientes = mutableListOf<String>()
        for (key in allKeys) {
            val clienteData = sharedPreferences.getString(key, null)
            if (clienteData != null) {
                clientes.add(clienteData)
            }
        }

        // Configurar el RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ClienteAdapter(
            this,
            clientes,
            onItemClick = { position ->
                // Mostrar los datos del cliente seleccionado en los campos de edición
                val clienteSeleccionado = clientes[position].split(";")
                etNombre.setText(clienteSeleccionado[0]) // Nombre
                etTelefono.setText(clienteSeleccionado[1]) // Teléfono

                // Botón Guardar - Actualizar datos del cliente
                btnGuardar.setOnClickListener {
                    val nuevoNombre = etNombre.text.toString().trim()
                    val nuevoTelefono = etTelefono.text.toString().trim()

                    if (nuevoNombre.isNotEmpty() && nuevoTelefono.isNotEmpty()) {
                        // Actualizar en SharedPreferences
                        val clienteKey = "cliente_$position"
                        val editor = sharedPreferences.edit()
                        editor.putString(clienteKey, "$nuevoNombre;$nuevoTelefono")
                        editor.apply()

                        // Actualizar en la lista y notificar al adaptador
                        clientes[position] = "$nuevoNombre;$nuevoTelefono"
                        adapter.notifyItemChanged(position)

                        Toast.makeText(this, "Cliente actualizado correctamente", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Rellena ambos campos", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            mostrarEliminar = false // Ocultar el botón de eliminar
        )
        recyclerView.adapter = adapter

        // Botón Atrás
        btnAtras.setOnClickListener {
            finish()
        }
    }
}
