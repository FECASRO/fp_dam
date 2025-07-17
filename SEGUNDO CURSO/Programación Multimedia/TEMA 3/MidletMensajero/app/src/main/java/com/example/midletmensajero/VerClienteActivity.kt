package com.example.midletmensajero

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VerClienteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_cliente)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewClientes)
        val btnAtras = findViewById<Button>(R.id.btnAtras)

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

        // Verificar si los datos fueron recuperados correctamente
        Log.d("VerClienteActivity", "Clientes recuperados: $clientes")

        // Configurar el RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ClienteAdapter(
            this,
            clientes,
            onDelete = { position ->
                // Eliminar cliente en la posición seleccionada
                val clienteEliminado = clientes[position]
                val clienteKey = "cliente_${clientes.indexOf(clienteEliminado)}"

                // Eliminar del SharedPreferences
                val editor = sharedPreferences.edit()
                editor.remove(clienteKey)
                editor.apply()

                // Eliminar de la lista de clientes
                clientes.removeAt(position)
                recyclerView.adapter?.notifyItemRemoved(position)

                // Mostrar mensaje de confirmación
                Toast.makeText(this, "Cliente eliminado", Toast.LENGTH_SHORT).show()
            },
            mostrarEliminar = false // Ocultar el botón "Eliminar" en esta actividad
        )

        // Botón ATRÁS
        btnAtras.setOnClickListener {
            finish()
        }
    }
}
