package com.example.votacionesapp
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ResultadosActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        val votosCandidato1 = intent.getIntExtra("VOTOS_CANDIDATO1", 0)
        val votosCandidato2 = intent.getIntExtra("VOTOS_CANDIDATO2", 0)
        val votosCandidato3 = intent.getIntExtra("VOTOS_CANDIDATO3", 0)

        val tvResultadoCandidato1 = findViewById<TextView>(R.id.tvResultadoCandidato1)
        val tvResultadoCandidato2 = findViewById<TextView>(R.id.tvResultadoCandidato2)
        val tvResultadoCandidato3 = findViewById<TextView>(R.id.tvResultadoCandidato3)
        val tvGanador = findViewById<TextView>(R.id.tvGanador)

        tvResultadoCandidato1.text = "Candidato 1: $votosCandidato1 votos"
        tvResultadoCandidato2.text = "Candidato 2: $votosCandidato2 votos"
        tvResultadoCandidato3.text = "Candidato 3: $votosCandidato3 votos"

        // Determinar el ganador
        val ganador = when {
            votosCandidato1 > votosCandidato2 && votosCandidato1 > votosCandidato3 -> "Candidato 1"
            votosCandidato2 > votosCandidato1 && votosCandidato2 > votosCandidato3 -> "Candidato 2"
            votosCandidato3 > votosCandidato1 && votosCandidato3 > votosCandidato2 -> "Candidato 3"
            else -> "Empate"
        }

        tvGanador.text = "Ganador: $ganador"
    }
}