package com.example.checkbox

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.checkbox.databinding.ActivityEquacoesBinding
import kotlin.math.sqrt

class EquacoesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEquacoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEquacoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.equacoes_titulo)
        }

        binding.btnCalcBhaskara.setOnClickListener { calcularBhaskara() }
        binding.btnLimparBhaskara.setOnClickListener {
            binding.edtBhaskaraA.setText("")
            binding.edtBhaskaraB.setText("")
            binding.edtBhaskaraC.setText("")
            binding.tvResultBhaskara.visibility = View.GONE
        }
    }

    private fun calcularBhaskara() {
        val a = binding.edtBhaskaraA.text.toString().toDoubleOrNull()
        val b = binding.edtBhaskaraB.text.toString().toDoubleOrNull()
        val c = binding.edtBhaskaraC.text.toString().toDoubleOrNull()

        if (a == null || b == null || c == null) { toast("Preencha todos os coeficientes"); return }
        if (a == 0.0) { toast("O coeficiente 'a' não pode ser zero"); return }

        val delta = b * b - 4 * a * c
        val sb = StringBuilder()
        sb.appendLine("Δ = b² − 4ac")
        sb.appendLine("Δ = %.4f".format(delta))
        sb.appendLine()

        when {
            delta > 0 -> {
                val x1 = (-b + sqrt(delta)) / (2 * a)
                val x2 = (-b - sqrt(delta)) / (2 * a)
                sb.appendLine("Duas raízes reais distintas:")
                sb.appendLine("x₁ = %.6f".format(x1))
                sb.append("x₂ = %.6f".format(x2))
                MainActivity.addHistorico("Bhaskara: x₁=%.4f, x₂=%.4f".format(x1, x2))
            }
            delta == 0.0 -> {
                val x = -b / (2 * a)
                sb.appendLine("Uma raiz real (dupla):")
                sb.append("x = %.6f".format(x))
                MainActivity.addHistorico("Bhaskara: x=%.4f (dupla)".format(x))
            }
            else -> {
                val parteReal = -b / (2 * a)
                val parteImg = sqrt(-delta) / (2 * a)
                sb.appendLine("Raízes complexas (sem solução real):")
                sb.appendLine("x₁ = %.4f + %.4fi".format(parteReal, parteImg))
                sb.append("x₂ = %.4f − %.4fi".format(parteReal, parteImg))
                MainActivity.addHistorico("Bhaskara: raízes complexas (Δ<0)")
            }
        }

        binding.tvResultBhaskara.text = sb.toString()
        binding.tvResultBhaskara.visibility = View.VISIBLE
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}