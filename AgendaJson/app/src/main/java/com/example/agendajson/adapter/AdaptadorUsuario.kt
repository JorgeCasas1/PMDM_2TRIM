package com.example.agendajson.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.agendajson.MainActivity
import com.example.agendajson.R
import com.example.agendajson.databinding.ItemUsuarioBinding
import com.example.agendajson.model.Usuario


class AdaptadorUsuario(val contexto: Context) :
    RecyclerView.Adapter<AdaptadorUsuario.MyHolder>() {
    private lateinit var listaUsuarios: ArrayList<Usuario>
    // Declaramos el objeto de la interfaz de callback para poder utilizarlo
    private lateinit var listener: OnUserAdapterListener

    init {
        // Inicializamos la lista de usuarios
        listaUsuarios = arrayListOf<Usuario>()
        // A partir de aquí vamos a la activity e implantamos el método
        listener = contexto as MainActivity
    }
    // xml -> ItemUsuarioBinding el nombre de mi xml.
    // Al relacionarlo directamente con el binding podemos acceder a los datos y no declararlos dentro del inner

    inner class MyHolder(var binding: ItemUsuarioBinding) : RecyclerView.ViewHolder(binding.root) {
        // Asociar toolbar de soporte -> le pasamos donde se ubica para que aparezca en cada Cardview
        // Para evitar que cada vez que scrolleo se multiplique lo metemos en el inner para evitar duplicados del menú
        init {
            binding.toolbar2.inflateMenu(R.menu.menu_user)
            // Que detecte la pulsación
            binding.toolbar2.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_agregar -> {

                    }
                    R.id.menu_detalle -> {
                        // A través de su pulsación
                        // bindingAdapterPosition -> coge la posicion del adapter que se crea
                        listener.onDetalleSelected(listaUsuarios[bindingAdapterPosition])
                    }
                }
                // Deb retornar esto sino no funcionará
                return@setOnMenuItemClickListener true
            }
        }
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

    fun añadirUsuario(usuario: Usuario) {
        listaUsuarios.add(usuario)
        // Se le mete el -1 porque se empieza la lista desde el 0 (esto es pq queremos actualizar la ultima posicion)
        notifyItemInserted(listaUsuarios.size - 1)
        Log.v("Entra", "Hola")
    }

    fun limpiarUsuario() {
        listaUsuarios.clear()
        notifyDataSetChanged()
    }

    // De esta forma comunicamos el boton detalle del menu del cardview
    interface OnUserAdapterListener {
        // Aqui metemos los datos que quiero comunicar
        fun onDetalleSelected(usuario: Usuario);
    }

}