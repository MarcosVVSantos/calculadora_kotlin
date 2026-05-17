package com.example.checkbox

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.checkbox.databinding.ActivityEstatisticaBinding

class EstatisticaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEstatisticaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstatisticaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.estat_titulo)
        }

        binding.btnCalcEstat.setOnClickListener { calcularEstatisticas() }
        binding.btnLimparEstat.setOnClickListener {
            binding.edtListaNumeros.setText("")
            binding.tvResultEstat.visibility = View.GONE
        }
    }

    private fun calcularEstatisticas() {
        val input = binding.edtListaNumeros.text.toString().trim()
        if (input.isEmpty()) { toast("Digite pelo menos um número"); return }

        val tokens = input.split(Regex("[,;\\s]+")).filter { it.isNotEmpty() }
        val numeros = tokens.mapNotNull { it.toDoubleOrNull() }

        if (numeros.isEmpty()) { toast("Nenhum número válido encontrado"); return }
        if (numeros.size < tokens.size) {
            toast("Alguns valores foram ignorados por serem inválidos")
        }

        val media = numeros.sum() / numeros.size
        val mediana = calcularMediana(numeros)
        val moda = calcularModa(numeros)

        val sb = StringBuilder()
        sb.appendLine("n = ${numeros.size} valores")
        sb.appendLine()
        sb.appendLine("Média = %.4f".format(media))
        sb.appendLine("Mediana = %.4f".format(mediana))
        sb.append("Moda = $moda")

        binding.tvResultEstat.text = sb.toString()
        binding.tvResultEstat.visibility = View.VISIBLE
        MainActivity.addHistorico("Estatística: Média=%.4f".format(media))
    }

    private fun calcularMediana(nums: List<Double>): Double {
        val sorted = nums.sorted()
        val n = sorted.size
        return if (n % 2 == 0) {
            (sorted[n / 2 - 1] + sorted[n / 2]) / 2.0
        } else {
            sorted[n / 2]
        }
    }

    private fun calcularModa(nums: List<Double>): String {
        val freq = nums.groupingBy { it }.eachCount()
        val maxFreq = freq.values.maxOrNull() ?: 1
        if (maxFreq == 1) return "Amodal (nenhum valor se repete)"
        val modas = freq.filter { it.value == maxFreq }.keys.sorted()
        return modas.joinToString(", ") { "%.4f".format(it) } +
                " (freq: $maxFreq)"
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}