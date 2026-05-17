package com.example.checkbox

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.checkbox.databinding.ActivityTrigonometriaBinding
import kotlin.math.*

class TrigonometriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrigonometriaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrigonometriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.trig_titulo)
        }

        binding.btnCalcTrigGraus.setOnClickListener { calcularDeGraus() }
        binding.btnLimparTrigGraus.setOnClickListener {
            binding.edtTrigGraus.setText("")
            binding.tvResultTrigGraus.visibility = View.GONE
        }

        binding.btnCalcTrigRad.setOnClickListener { calcularDeRadianos() }
        binding.btnLimparTrigRad.setOnClickListener {
            binding.edtTrigRad.setText("")
            binding.tvResultTrigRad.visibility = View.GONE
        }

        binding.btnCalcTrigInv.setOnClickListener { calcularInversas() }
        binding.btnLimparTrigInv.setOnClickListener {
            binding.edtTrigInv.setText("")
            binding.tvResultTrigInv.visibility = View.GONE
        }
    }

    private fun calcularDeGraus() {
        val graus = binding.edtTrigGraus.text.toString().toDoubleOrNull()
        if (graus == null) { toast("Digite um ângulo válido"); return }
        val rad = Math.toRadians(graus)
        val sb = StringBuilder()
        sb.appendLine("%.2f° = %.6f rad".format(graus, rad))
        sb.appendLine("sen(%.2f°) = %.6f".format(graus, sin(rad)))
        sb.appendLine("cos(%.2f°) = %.6f".format(graus, cos(rad)))
        val tanVal = tan(rad)
        if (abs(cos(rad)) < 1e-10) {
            sb.append("tan(%.2f°) = indefinido".format(graus))
        } else {
            sb.append("tan(%.2f°) = %.6f".format(graus, tanVal))
        }
        binding.tvResultTrigGraus.text = sb.toString()
        binding.tvResultTrigGraus.visibility = View.VISIBLE
        MainActivity.addHistorico("Trig: sen(%.1f°)=%.4f".format(graus, sin(rad)))
    }

    private fun calcularDeRadianos() {
        val rad = binding.edtTrigRad.text.toString().toDoubleOrNull()
        if (rad == null) { toast("Digite um ângulo válido"); return }
        val graus = Math.toDegrees(rad)
        val sb = StringBuilder()
        sb.appendLine("%.6f rad = %.4f°".format(rad, graus))
        sb.appendLine("sen = %.6f".format(sin(rad)))
        sb.appendLine("cos = %.6f".format(cos(rad)))
        if (abs(cos(rad)) < 1e-10) {
            sb.append("tan = indefinido")
        } else {
            sb.append("tan = %.6f".format(tan(rad)))
        }
        binding.tvResultTrigRad.text = sb.toString()
        binding.tvResultTrigRad.visibility = View.VISIBLE
        MainActivity.addHistorico("Trig: sen(%.4f rad)=%.4f".format(rad, sin(rad)))
    }

    private fun calcularInversas() {
        val v = binding.edtTrigInv.text.toString().toDoubleOrNull()
        if (v == null) { toast("Digite um valor válido"); return }
        val sb = StringBuilder()
        if (v in -1.0..1.0) {
            sb.appendLine("arcsen(%.4f) = %.4f°".format(v, Math.toDegrees(asin(v))))
            sb.appendLine("arccos(%.4f) = %.4f°".format(v, Math.toDegrees(acos(v))))
        } else {
            sb.appendLine("arcsen e arccos: valor fora de [−1, 1]")
        }
        sb.append("arctan(%.4f) = %.4f°".format(v, Math.toDegrees(atan(v))))
        binding.tvResultTrigInv.text = sb.toString()
        binding.tvResultTrigInv.visibility = View.VISIBLE
        MainActivity.addHistorico("Trig Inv: arctan(%.4f)=%.4f°".format(v, Math.toDegrees(atan(v))))
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}