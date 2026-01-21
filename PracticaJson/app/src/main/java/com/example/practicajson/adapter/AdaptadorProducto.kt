package com.example.practicajson.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicajson.databinding.ItemJsonBinding
import com.example.practicajson.model.Producto

class AdaptadorProducto(val contexto: Context) :
    RecyclerView.Adapter<AdaptadorProducto.MyHolder>() {
    // Metemos una lista de los productos del JSON que mostraremos en el Cardview
    // Iniciamos la lista productos
    private var listaProductos: ArrayList<Producto> = arrayListOf<Producto>()


    // Le paso el nombre de mi xml en este caso es ItemJsonBinding
    inner class MyHolder(var binding: ItemJsonBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        // Iniciamos el binding dentro del adapter
        val binding: ItemJsonBinding =
            ItemJsonBinding.inflate(LayoutInflater.from(contexto), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        // Cogemos los valores que quiero que se vean en el CardView
        val producto: Producto = listaProductos[position]
        holder.binding.textoTitulo.text = producto.title.toString()
        holder.binding.textoCategoria.text = producto.category.toString()
        Glide.with(contexto).load(producto.images).into(holder.binding.imagenJson)

    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }


}