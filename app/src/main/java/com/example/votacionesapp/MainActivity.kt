package com.example.votacionesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNumeroElectores = findViewById<EditText>(R.id.etNumeroElectores)
        val btnIniciarVotacion = findViewById<Button>(R.id.btnIniciarVotacion)

        btnIniciarVotacion.setOnClickListener {
            val numeroElectoresStr = etNumeroElectores.text.toString()

            if (numeroElectoresStr.isEmpty()) {
                Toast.makeText(this, "Ingrese el número de electores", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val numeroElectores = numeroElectoresStr.toInt()

            if (numeroElectores <= 0) {
                Toast.makeText(this, "El número de electores debe ser mayor a 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, VotacionActivity::class.java)
            intent.putExtra("NUMERO_ELECTORES", numeroElectores)
            startActivity(intent)
        }
    }
}