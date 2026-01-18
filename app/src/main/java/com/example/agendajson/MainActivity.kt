package com.example.agendajson

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.agendajson.adapter.AdaptadorUsuario
import com.example.agendajson.databinding.ActivityMainBinding
import com.example.agendajson.model.Usuario
import com.google.gson.Gson
import androidx.appcompat.widget.Toolbar
import com.example.agendajson.ui.dialog.DialogoAyuda
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    // private lateinit var listaUsuarios: ArrayList<Usuario> El adaptador se crea con la lista vacia
    private lateinit var adaptadorUsuario: AdaptadorUsuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        instancias()
        initGUI()
        realizarJSON()
    }

    private fun instancias() {
        adaptadorUsuario = AdaptadorUsuario(this)
    }

    private fun initGUI() {
        binding.RecycleUsuarios.adapter = adaptadorUsuario
        binding.RecycleUsuarios.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    private fun realizarJSON() {
        // peticion a crear -> JSONObjectRequest
        val url = "https://dummyjson.com/users"
        val request: JsonObjectRequest = JsonObjectRequest(url, { procesarPeticionJSON(it) }, {
            Log.v("error", "Error en la conexion")
        })
        // Añadir a la cola de peticiones
        //2. Añadir a la cola las peticiones
        Volley.newRequestQueue(applicationContext).add(request)
        // Meter dependencia GSON en dependencias implementation 'com.google.code.gson:gson:2.13.2'
        // quicktype metemos en la url en el url https://dummyjson.com/users/1
        // Copiamos en la web y lo pasamamos al lenguaje que queramos copiamos y pegamos la clase concreta
    }

    private fun procesarPeticionJSON(param: JSONObject) {
        val gson: Gson = Gson()
        val listaJson = arrayListOf<Usuario>()
        // En el log cat si podemos conexion podemos ver el JSON
        val usuariosJSONArray = param.getJSONArray("users")
        // Me da elemento asociado al tag users
        for (i in 0..usuariosJSONArray.length() - 1) {
            val usuarioJSON = usuariosJSONArray.getJSONObject(i)
            // convertir objeto gson
            val usuario: Usuario = gson.fromJson(usuarioJSON.toString(), Usuario::class.java)
            // Log.v("conexion", usuario.firstName.toString())
            adaptadorUsuario.añadirUsuario(usuario)
        }
    }

    // Esto es para que aparezca el menu desplegable en la interfaz
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    // Sirve para mostrar la logica aplicada del ui.dialog
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_filtrar -> {

            }

            R.id.menu_ayuda -> {
                val dialogAyuda = DialogoAyuda()
                dialogAyuda.show(supportFragmentManager, null)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}


