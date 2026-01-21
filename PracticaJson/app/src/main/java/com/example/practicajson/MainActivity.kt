package com.example.practicajson

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.toolbox.JsonObjectRequest
import com.example.practicajson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Metemos la url de donde vamos a obtener los datos que queremos del JSON
    private val urlJSON: String = "https://dummyjson.com/products/search?q=phone"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    private fun instancias() {

    }

    private fun initGUI() {

    }

    private fun realizarJSON(url: String) {

    }

    private fun procesarPeticion() {

    }

}