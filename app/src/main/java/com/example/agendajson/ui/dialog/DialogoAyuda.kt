package com.example.agendajson.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogoAyuda: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 1. Crear un builder nos permite saber que va en los botones, listas....
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())

        // titulo
        builder.setTitle("Selecciona un género a filtar")
        // msj lista
        builder.setMessage("Esta app es realizada por Jorge Casas")
        // botones -> neutro negativo/positivo
        builder.setPositiveButton("SI",{view,posicion->

        })

        // 2. Creo el dialogo
        return builder.create()

    }
}