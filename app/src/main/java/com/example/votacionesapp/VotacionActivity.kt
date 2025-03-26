package com.example.votacionesapp
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VotacionActivity : AppCompatActivity() {

    private var numeroElectores = 0
    private var electorActual = 1
    private var votosCandidato1 = 0
    private var votosCandidato2 = 0
    private var votosCandidato3 = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_votacion)

        numeroElectores = intent.getIntExtra("NUMERO_ELECTORES", 0)

        val etEdad = findViewById<EditText>(R.id.etEdad)
        val rgCandidatos = findViewById<RadioGroup>(R.id.rgCandidatos)
        val btnVotar = findViewById<Button>(R.id.btnVotar)
        val tvContador = findViewById<TextView>(R.id.tvContador)

        tvContador.text = "Elector $electorActual de $numeroElectores"

        btnVotar.setOnClickListener {
            val edadStr = etEdad.text.toString()

            if (edadStr.isEmpty()) {
                Toast.makeText(this, "Ingrese su edad", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val edad = edadStr.toInt()

            if (edad < 18) {
                Toast.makeText(this, "Debe ser mayor de edad para votar", Toast.LENGTH_SHORT).show()
                etEdad.text.clear()
                return@setOnClickListener
            }

            val candidatoSeleccionado = rgCandidatos.checkedRadioButtonId

            if (candidatoSeleccionado == -1) {
                Toast.makeText(this, "Seleccione un candidato", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Registrar voto
            when (candidatoSeleccionado) {
                R.id.rbCandidato1 -> votosCandidato1++
                R.id.rbCandidato2 -> votosCandidato2++
                R.id.rbCandidato3 -> votosCandidato3++
            }

            // Limpiar campos para el siguiente elector
            etEdad.text.clear()
            rgCandidatos.clearCheck()

            // Verificar si ya votaron todos los electores
            if (electorActual == numeroElectores) {
                // Ir a la pantalla de resultados
                val intent = Intent(this, ResultadosActivity::class.java)
                intent.putExtra("VOTOS_CANDIDATO1", votosCandidato1)
                intent.putExtra("VOTOS_CANDIDATO2", votosCandidato2)
                intent.putExtra("VOTOS_CANDIDATO3", votosCandidato3)
                startActivity(intent)
                finish()
            } else {
                // Actualizar contador para el siguiente elector
                electorActual++
                tvContador.text = "Elector $electorActual de $numeroElectores"
            }
        }
    }
}