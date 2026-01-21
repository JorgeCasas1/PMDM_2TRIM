package com.example.agendajson.ui_dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.agendajson.MainActivity
import com.example.agendajson.adapter.AdaptadorUsuario
import com.example.agendajson.databinding.CuadroDialogoUsuarioBinding
import com.example.agendajson.model.Usuario

class DialogUser : DialogFragment() {

    companion object {
        // Si quiero comunicar al cuadro de dialogo algo
        // 1. Paso. companion object con un metodo -> retornamos objeto de la clase DialogUser
        fun newInstance(user: Usuario): DialogUser {
            val dialog = DialogUser()
            val bundle = Bundle()
            // Tenemos que hacer Serializable a la clase
            bundle.putSerializable("user", user)
            dialog.arguments = bundle
            return dialog
        }
    }

    // Construcción de parte gráfica para que se vea en la pantalla
    private lateinit var binding: CuadroDialogoUsuarioBinding
    private lateinit var user: Usuario
    // Lo necesitamos el user para castearlo en onAttach

    // 2. Paso. Recupero los argumentos t los hago variables de clase
    // Ponemos el attach despues del companionObject
    override fun onAttach(context: Context) {
        super.onAttach(context)
        user = this.arguments?.getSerializable("user") as Usuario
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        // titulo
        // mjs
        // boton
        binding = CuadroDialogoUsuarioBinding.inflate(layoutInflater, null, false)
        builder.setView(binding.root)
        binding.textoEmail.text = user.email.toString()
        binding.textoApellidoDetalle.text = user.lastName.toString()
        binding.textoEdadDetalle.text = user.age.toString()
        binding.textoNombreDetalle.text = user.firstName.toString()
        Glide.with(requireContext()).load(user.image).into(binding.imagenDetalle)
        return builder.create()
    }
}

