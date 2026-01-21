package com.example.agendajson.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.agendajson.R
import com.example.agendajson.databinding.ActivityMainBinding
import com.example.agendajson.databinding.ItemUsuarioBinding
import com.example.agendajson.model.Usuario
import kotlin.math.log


class AdaptadorUsuario(val contexto: Context) :
    RecyclerView.Adapter<AdaptadorUsuario.MyHolder>() {
    private lateinit var listaUsuarios: ArrayList<Usuario>

    init {
        // Inicializamos la lista de usuarios
        listaUsuarios = arrayListOf<Usuario>()
    }
    // xml -> ItemUsuarioBinding el nombre de mi xml.
    // Al relacionarlo directamente con el binding podemos acceder a los datos y no declararlos dentro del inner

    inner class MyHolder(var binding: ItemUsuarioBinding) : RecyclerView.ViewHolder(binding.root) {

        /*val textoNombre: TextView = vista.findViewById(R.id.textoNombre)
        val textoApellido: TextView = vista.findViewById(R.id.textoApellido)
        val imagenUsuario: ImageView = vista.findViewById(R.id.imagenUsuario)*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        // Inicialización de binding dentro del Adapter
        val binding: ItemUsuarioBinding =
            ItemUsuarioBinding.inflate(LayoutInflater.from(contexto), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        // Cogemos los valores que quiero que se vean en el cardview.
        val usuario: Usuario = listaUsuarios[position]
        // Editamos lo que quieres que aparezca en el toolbar (en este caso el género)
        holder.binding.toolbar2.title = usuario.gender
        holder.binding.textoNombre.text = usuario.firstName.toString()
        holder.binding.textoApellido.text = usuario.lastName.toString()

        // Glide. Requiere dependencia no te olvides de importarla y sincronizar
        Glide.with(contexto).load(usuario.image).into(holder.binding.imagenUsuario)

    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    fun añadirUsuario(usuario: Usuario){
        listaUsuarios.add(usuario)
        // Se le mete el -1 porque se empieza la lista desde el 0 (esto es pq queremos actualizar la ultima posicion)
        notifyItemInserted(listaUsuarios.size-1)
        Log.v("Entra","Hola")
    }
    fun limpiarUsuario(){
        listaUsuarios.clear()
        notifyDataSetChanged()
    }

}
