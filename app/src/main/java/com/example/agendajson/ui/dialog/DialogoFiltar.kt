package com.example.agendajson.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.agendajson.MainActivity

// Extiende de Dialog.fragment.app
class DialogoFiltar : DialogFragment() {
    // 2. Declarlar para utilizarla (interfaz callback)
    private lateinit var listener: OnDialgoGeneralListener
    private lateinit var listaOpciones: Array<CharSequence>
    var seleccion: Int = -1
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listaOpciones = arrayOf("male", "female", "all")
        // 3. Declaramos el contexto (interfaz callback)
        // 4. Implementar el método sobreescribiendolo en el main (interfaz callback)
        listener = context as MainActivity
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Selecciona el genero a filetrar")
        // Lista de opciones declarada arriba -> al ser -1 significa que no esta ninguno seleccionado de ("Male","Female","All")
        // No saldrá un cuadro de dialogo que nos permitirá elgir o Male, Female o All.
        builder.setSingleChoiceItems(listaOpciones, -1, { _, p ->
            seleccion = p

        })
        // Otra opción
        // builder.setMultiChoiceItems(listaOpciones,null,{vista,posicion,boolean})
        // Lista de opciones
        // posicion -> el 0 es posicion neutra, 1 negativo, 2 positivo en caso de utilizar (v,posicion->{})
        // lista de opciones -msj
        // builder.setItems() pone todos los elementos de una lista sin cambiar la selección.
        builder.setPositiveButton("Ok", { _, _ ->
            // esto produce el paso de datos.
            listener.onGeneroSelected(listaOpciones[seleccion].toString())

        })
        return builder.create()
    }

    // 1. Definir una interfaz (interfaz callback)
    // Para trasladar el dato que queremos filtrar que sería Masculino, femenino o todo.
    interface OnDialgoGeneralListener {
        fun onGeneroSelected(genero: String);
    }
}