package com.example.checkbox

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.checkbox.databinding.ActivityEnergiaMecanicaBinding
import kotlin.math.cos

class EnergiaMecanicaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnergiaMecanicaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnergiaMecanicaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.energia_titulo)
        }

        binding.btnCalcTrab.setOnClickListener { calcularTrabalho() }
        binding.btnLimparTrab.setOnClickListener {
            binding.edtTrabF.setText("")
            binding.edtTrabD.setText("")
            binding.edtTrabTheta.setText("")
            binding.tvResultTrab.visibility = View.GONE
        }

        binding.btnCalcEc.setOnClickListener { calcularEc() }
        binding.btnLimparEc.setOnClickListener {
            binding.edtEcMassa.setText("")
            binding.edtEcVel.setText("")
            binding.tvResultEc.visibility = View.GONE
        }

        binding.btnCalcEp.setOnClickListener { calcularEp() }
        binding.btnLimparEp.setOnClickListener {
            binding.edtEpMassa.setText("")
            binding.edtEpAltura.setText("")
            binding.tvResultEp.visibility = View.GONE
        }
    }

    private fun calcularTrabalho() {
        val f = binding.edtTrabF.text.toString().toDoubleOrNull()
        val d = binding.edtTrabD.text.toString().toDoubleOrNull()
        val theta = binding.edtTrabTheta.text.toString().toDoubleOrNull()
        if (f == null || d == null || theta == null) { toast("Preencha todos os campos"); return }
        val thetaRad = Math.toRadians(theta)
        val trabalho = f * d * cos(thetaRad)
        binding.tvResultTrab.text = "τ = %.4f J".format(trabalho)
        binding.tvResultTrab.visibility = View.VISIBLE
        MainActivity.addHistorico("Trabalho: τ = %.4f J".format(trabalho))
    }

    private fun calcularEc() {
        val m = binding.edtEcMassa.text.toString().toDoubleOrNull()
        val v = binding.edtEcVel.text.toString().toDoubleOrNull()
        if (m == null || v == null) { toast("Preencha massa e velocidade"); return }
        val ec = 0.5 * m * v * v
        binding.tvResultEc.text = "Ec = %.4f J".format(ec)
        binding.tvResultEc.visibility = View.VISIBLE
        MainActivity.addHistorico("Energia Cinética: Ec = %.4f J".format(ec))
    }

    private fun calcularEp() {
        val m = binding.edtEpMassa.text.toString().toDoubleOrNull()
        val h = binding.edtEpAltura.text.toString().toDoubleOrNull()
        if (m == null || h == null) { toast("Preencha massa e altura"); return }
        val ep = m * 9.8 * h
        binding.tvResultEp.text = "Ep = %.4f J".format(ep)
        binding.tvResultEp.visibility = View.VISIBLE
        MainActivity.addHistorico("Energia Potencial: Ep = %.4f J".format(ep))
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}