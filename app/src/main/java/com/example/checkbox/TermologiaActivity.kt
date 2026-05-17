package com.example.checkbox

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.checkbox.databinding.ActivityTermologiaBinding

class TermologiaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTermologiaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermologiaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.termo_titulo)
        }

        binding.btnCalcDilat.setOnClickListener { calcularDilatacao() }
        binding.btnLimparDilat.setOnClickListener {
            binding.edtDilatL0.setText("")
            binding.edtDilatAlpha.setText("")
            binding.edtDilatDeltaT.setText("")
            binding.tvResultDilat.visibility = View.GONE
        }

        binding.btnCalcCalor.setOnClickListener { calcularCalor() }
        binding.btnLimparCalor.setOnClickListener {
            binding.edtCalorM.setText("")
            binding.edtCalorC.setText("")
            binding.edtCalorDeltaT.setText("")
            binding.tvResultCalor.visibility = View.GONE
        }
    }

    private fun calcularDilatacao() {
        val l0 = binding.edtDilatL0.text.toString().toDoubleOrNull()
        val alpha = binding.edtDilatAlpha.text.toString().toDoubleOrNull()
        val dt = binding.edtDilatDeltaT.text.toString().toDoubleOrNull()
        if (l0 == null || alpha == null || dt == null) { toast("Preencha todos os campos"); return }
        val deltaL = l0 * alpha * dt
        val lFinal = l0 + deltaL
        binding.tvResultDilat.text = "ΔL = %.6f m\nL final = %.6f m".format(deltaL, lFinal)
        binding.tvResultDilat.visibility = View.VISIBLE
        MainActivity.addHistorico("Dilatação Linear: ΔL = %.6f m".format(deltaL))
    }

    private fun calcularCalor() {
        val m = binding.edtCalorM.text.toString().toDoubleOrNull()
        val c = binding.edtCalorC.text.toString().toDoubleOrNull()
        val dt = binding.edtCalorDeltaT.text.toString().toDoubleOrNull()
        if (m == null || c == null || dt == null) { toast("Preencha todos os campos"); return }
        val q = m * c * dt
        binding.tvResultCalor.text = "Q = %.4f J".format(q)
        binding.tvResultCalor.visibility = View.VISIBLE
        MainActivity.addHistorico("Calor: Q = %.4f J".format(q))
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}