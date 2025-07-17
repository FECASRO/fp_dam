package com.example.midletmensajero

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClienteAdapter(
    private val context: Context,
    private val clientes: MutableList<String>, // Lista mutable de clientes
    private val onDelete: (Int) -> Unit, // Función para eliminar un cliente
    private val mostrarEliminar: Boolean = true // Controlar visibilidad del botón "Eliminar"

) : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cliente, parent, false)
        return ClienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val clienteData = clientes[position]
        val datos = clienteData.split(":")

        if (datos.size == 2) {
            holder.nombreTextView.text = "Nombre: ${datos[0]}"
            holder.telefonoTextView.text = "Teléfono: ${datos[1]}"
        } else {
            Log.e("ClienteAdapter", "Datos mal formateados: $clienteData")
            holder.nombreTextView.text = "Nombre: Desconocido"
            holder.telefonoTextView.text = "Teléfono: Desconocido"
        }

        // Configurar visibilidad del botón "Eliminar"
        if (mostrarEliminar) {
            holder.eliminarButton.visibility = View.VISIBLE
            holder.eliminarButton.setOnClickListener {
                onDelete(position)
            }
        } else {
            holder.eliminarButton.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return clientes.size
    }

    class ClienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.tvNombre)
        val telefonoTextView: TextView = itemView.findViewById(R.id.tvTelefono)
        val eliminarButton: Button = itemView.findViewById(R.id.btnEliminar)
    }
}
