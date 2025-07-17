package com.example.midletmensajero

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.midletmensajero.databinding.ItemOpcionClienteBinding // Cambiar "com.example.midletmensajero" por tu paquete

class ClientesAdapter(
    private val opciones: List<String>,
    private val onClick: (String) -> Unit // Listener para manejar clics
) : RecyclerView.Adapter<ClientesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textoOpcion = view.findViewById<TextView>(R.id.texto_opcion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_opcion_cliente, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val opcion = opciones[position]
        holder.textoOpcion.text = opcion
        holder.itemView.setOnClickListener { onClick(opcion) } // Configurar clic
    }

    override fun getItemCount(): Int = opciones.size
}
