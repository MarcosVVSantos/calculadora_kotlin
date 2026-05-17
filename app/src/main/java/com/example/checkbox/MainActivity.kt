package com.example.checkbox

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.checkbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private val historico = mutableListOf<String>()

        fun addHistorico(entry: String) {
            historico.add(0, entry)
            if (historico.size > 10) historico.removeAt(10)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFisica.setOnClickListener {
            startActivity(Intent(this, FisicaMenuActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.btnMatematica.setOnClickListener {
            startActivity(Intent(this, MatematicaMenuActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.btnLimparHistorico.setOnClickListener {
            historico.clear()
            atualizarHistorico()
        }
    }

    override fun onResume() {
        super.onResume()
        atualizarHistorico()
    }

    private fun atualizarHistorico() {
        if (historico.isEmpty()) {
            binding.tvHistoricoVazio.visibility = View.VISIBLE
            binding.tvHistoricoLista.visibility = View.GONE
        } else {
            binding.tvHistoricoVazio.visibility = View.GONE
            binding.tvHistoricoLista.visibility = View.VISIBLE
            binding.tvHistoricoLista.text = historico.joinToString("\n")
        }
    }
}