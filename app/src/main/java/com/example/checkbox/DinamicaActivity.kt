package com.example.checkbox

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.checkbox.databinding.ActivityDinamicaBinding

class DinamicaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDinamicaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinamicaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.dinamica_titulo)
        }

        binding.btnCalcForca.setOnClickListener { calcularForca() }
        binding.btnLimparForca.setOnClickListener {
            binding.edtForcaMassa.setText("")
            binding.edtForcaAcel.setText("")
            binding.tvResultForca.visibility = View.GONE
        }

        binding.btnCalcMassa.setOnClickListener { calcularMassa() }
        binding.btnLimparMassa.setOnClickListener {
            binding.edtMassaForca.setText("")
            binding.edtMassaAcel.setText("")
            binding.tvResultMassa.visibility = View.GONE
        }

        binding.btnCalcAcelDin.setOnClickListener { calcularAceleracao() }
        binding.btnLimparAcelDin.setOnClickListener {
            binding.edtAcelForca.setText("")
            binding.edtAcelMassa.setText("")
            binding.tvResultAcelDin.visibility = View.GONE
        }
    }

    private fun calcularForca() {
        val m = binding.edtForcaMassa.text.toString().toDoubleOrNull()
        val a = binding.edtForcaAcel.text.toString().toDoubleOrNull()
        if (m == null || a == null) { toast("Preencha massa e aceleração"); return }
        val f = m * a
        binding.tvResultForca.text = "F = %.4f N".format(f)
        binding.tvResultForca.visibility = View.VISIBLE
        MainActivity.addHistorico("Dinâmica: F = %.4f N".format(f))
    }

    private fun calcularMassa() {
        val f = binding.edtMassaForca.text.toString().toDoubleOrNull()
        val a = binding.edtMassaAcel.text.toString().toDoubleOrNull()
        if (f == null || a == null) { toast("Preencha força e aceleração"); return }
        if (a == 0.0) { toast("Aceleração não pode ser zero"); return }
        val m = f / a
        binding.tvResultMassa.text = "m = %.4f kg".format(m)
        binding.tvResultMassa.visibility = View.VISIBLE
        MainActivity.addHistorico("Dinâmica: m = %.4f kg".format(m))
    }

    private fun calcularAceleracao() {
        val f = binding.edtAcelForca.text.toString().toDoubleOrNull()
        val m = binding.edtAcelMassa.text.toString().toDoubleOrNull()
        if (f == null || m == null) { toast("Preencha força e massa"); return }
        if (m == 0.0) { toast("Massa não pode ser zero"); return }
        val a = f / m
        binding.tvResultAcelDin.text = "a = %.4f m/s²".format(a)
        binding.tvResultAcelDin.visibility = View.VISIBLE
        MainActivity.addHistorico("Dinâmica: a = %.4f m/s²".format(a))
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}