package com.example.midletmensajero

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ClientesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientes)

        // Configurar RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewOpciones)
        val opciones = listOf(
            "Ver los datos de un cliente",
            "Añadir un nuevo cliente",
            "Eliminar un cliente",
            "Modificar los datos de un cliente",
            "Volver a la pantalla principal"
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ClientesAdapter(opciones) { opcion ->
            manejarOpcionSeleccionada(opcion)
        }

        // Manejar botón "ATRÁS"
        val botonAtras = findViewById<Button>(R.id.atrasenlistadoclientes)
        botonAtras.setOnClickListener {
            finish() // Finaliza esta Activity y vuelve atrás
        }
    }

    private fun manejarOpcionSeleccionada(opcion: String) {
        when (opcion) {
            "Ver los datos de un cliente" -> startActivity(Intent(this, VerClienteActivity::class.java))
            "Añadir un nuevo cliente" -> startActivity(Intent(this, AnadirClienteActivity::class.java))
            "Eliminar un cliente" -> startActivity(Intent(this, EliminarClienteActivity::class.java))
            "Modificar los datos de un cliente" -> startActivity(Intent(this, ModificarClienteActivity::class.java))
            "Volver a la pantalla principal" -> finish()
        }
    }
}
