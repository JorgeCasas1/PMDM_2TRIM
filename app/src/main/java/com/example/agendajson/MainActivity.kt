package com.example.agendajson

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.agendajson.databinding.ActivityMainBinding
import com.example.agendajson.model.Usuario
import com.google.gson.Gson
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        realizarJSON()
    }


    private fun realizarJSON() {
        // peticion a crear-> JSONObjectRequest
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
        // En el log cat si podemos conexion podemos ver el JSON
        val usuariosJSONArray = param.getJSONArray("users")
        // Me da elemento asociado al tag users
        for (i in 0..usuariosJSONArray.length() - 1) {
            val usuarioJSON = usuariosJSONArray.getJSONObject(i)
            // convertir objeto gson
            val usuario: Usuario = gson.fromJson(usuarioJSON.toString(), Usuario::class.java)
            Log.v("conexion", usuario.firstName.toString())
        }
    }
    // Sacar carta con imagen, nombre y apellido
}


