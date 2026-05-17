package com.example.checkbox

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.checkbox.databinding.ActivityOpticaBinding

class OpticaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpticaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpticaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.optica_titulo)
        }

        binding.btnCalcOptica.setOnClickListener { calcularFocal() }
        binding.btnLimparOptica.setOnClickListener {
            binding.edtOpticaP.setText("")
            binding.edtOpticaPL.setText("")
            binding.tvResultOptica.visibility = View.GONE
        }

        binding.btnCalcAmp.setOnClickListener { calcularAmpliacao() }
        binding.btnLimparAmp.setOnClickListener {
            binding.edtAmpP.setText("")
            binding.edtAmpPL.setText("")
            binding.tvResultAmp.visibility = View.GONE
        }
    }

    private fun calcularFocal() {
        val p = binding.edtOpticaP.text.toString().toDoubleOrNull()
        val pl = binding.edtOpticaPL.text.toString().toDoubleOrNull()
        if (p == null || pl == null) { toast("Preencha p e p'"); return }
        if (p + pl == 0.0) { toast("p + p' não pode ser zero"); return }
        if (p == 0.0 || pl == 0.0) { toast("p e p' não podem ser zero"); return }
        val f = (p * pl) / (p + pl)
        val vergencia = 1.0 / (f / 100.0)
        binding.tvResultOptica.text = "f = %.4f cm\nVergência = %.4f di".format(f, vergencia)
        binding.tvResultOptica.visibility = View.VISIBLE
        MainActivity.addHistorico("Óptica: f = %.4f cm".format(f))
    }

    private fun calcularAmpliacao() {
        val p = binding.edtAmpP.text.toString().toDoubleOrNull()
        val pl = binding.edtAmpPL.text.toString().toDoubleOrNull()
        if (p == null || pl == null) { toast("Preencha p e p'"); return }
        if (p == 0.0) { toast("p não pode ser zero"); return }
        val m = -pl / p
        val tipo = when {
            m > 0 -> "direta"
            m < 0 -> "invertida"
            else -> "nula"
        }
        binding.tvResultAmp.text = "M = %.4f\nImagem $tipo".format(m)
        binding.tvResultAmp.visibility = View.VISIBLE
        MainActivity.addHistorico("Ampliação: M = %.4f".format(m))
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}