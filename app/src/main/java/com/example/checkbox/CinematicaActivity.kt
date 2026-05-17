package com.example.checkbox

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.checkbox.databinding.ActivityCinematicaBinding

class CinematicaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCinematicaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCinematicaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.cinematica_titulo)
        }

        binding.btnCalcVel.setOnClickListener { calcularVelocidade() }
        binding.btnLimparVel.setOnClickListener {
            binding.edtVelDeltaS.setText("")
            binding.edtVelDeltaT.setText("")
            binding.tvResultVel.visibility = View.GONE
        }

        binding.btnCalcAcel.setOnClickListener { calcularAceleracao() }
        binding.btnLimparAcel.setOnClickListener {
            binding.edtAcelDeltaV.setText("")
            binding.edtAcelDeltaT.setText("")
            binding.tvResultAcel.visibility = View.GONE
        }

        binding.btnCalcMru.setOnClickListener { calcularMRU() }
        binding.btnLimparMru.setOnClickListener {
            binding.edtMruS0.setText("")
            binding.edtMruV.setText("")
            binding.edtMruT.setText("")
            binding.tvResultMru.visibility = View.GONE
        }

        binding.btnCalcMruv.setOnClickListener { calcularMRUV() }
        binding.btnLimparMruv.setOnClickListener {
            binding.edtMruvS0.setText("")
            binding.edtMruvV0.setText("")
            binding.edtMruvA.setText("")
            binding.edtMruvT.setText("")
            binding.tvResultMruv.visibility = View.GONE
        }

        binding.btnCalcMruvVf.setOnClickListener { calcularMRUVVf() }
        binding.btnLimparMruvVf.setOnClickListener {
            binding.edtMruvVfV0.setText("")
            binding.edtMruvVfA.setText("")
            binding.edtMruvVfT.setText("")
            binding.tvResultMruvVf.visibility = View.GONE
        }
    }

    private fun calcularVelocidade() {
        val ds = binding.edtVelDeltaS.text.toString().toDoubleOrNull()
        val dt = binding.edtVelDeltaT.text.toString().toDoubleOrNull()
        if (ds == null || dt == null) { toast("Preencha Δs e Δt"); return }
        if (dt == 0.0) { toast("Δt não pode ser zero"); return }
        val v = ds / dt
        binding.tvResultVel.text = "v = %.4f m/s".format(v)
        binding.tvResultVel.visibility = View.VISIBLE
        MainActivity.addHistorico("Velocidade Média: v = %.4f m/s".format(v))
    }

    private fun calcularAceleracao() {
        val dv = binding.edtAcelDeltaV.text.toString().toDoubleOrNull()
        val dt = binding.edtAcelDeltaT.text.toString().toDoubleOrNull()
        if (dv == null || dt == null) { toast("Preencha Δv e Δt"); return }
        if (dt == 0.0) { toast("Δt não pode ser zero"); return }
        val a = dv / dt
        binding.tvResultAcel.text = "a = %.4f m/s²".format(a)
        binding.tvResultAcel.visibility = View.VISIBLE
        MainActivity.addHistorico("Aceleração Média: a = %.4f m/s²".format(a))
    }

    private fun calcularMRU() {
        val s0 = binding.edtMruS0.text.toString().toDoubleOrNull()
        val v = binding.edtMruV.text.toString().toDoubleOrNull()
        val t = binding.edtMruT.text.toString().toDoubleOrNull()
        if (s0 == null || v == null || t == null) { toast("Preencha todos os campos"); return }
        val s = s0 + v * t
        binding.tvResultMru.text = "s = %.4f m".format(s)
        binding.tvResultMru.visibility = View.VISIBLE
        MainActivity.addHistorico("MRU: s = %.4f m".format(s))
    }

    private fun calcularMRUV() {
        val s0 = binding.edtMruvS0.text.toString().toDoubleOrNull()
        val v0 = binding.edtMruvV0.text.toString().toDoubleOrNull()
        val a = binding.edtMruvA.text.toString().toDoubleOrNull()
        val t = binding.edtMruvT.text.toString().toDoubleOrNull()
        if (s0 == null || v0 == null || a == null || t == null) { toast("Preencha todos os campos"); return }
        val s = s0 + v0 * t + 0.5 * a * t * t
        binding.tvResultMruv.text = "s = %.4f m".format(s)
        binding.tvResultMruv.visibility = View.VISIBLE
        MainActivity.addHistorico("MRUV posição: s = %.4f m".format(s))
    }

    private fun calcularMRUVVf() {
        val v0 = binding.edtMruvVfV0.text.toString().toDoubleOrNull()
        val a = binding.edtMruvVfA.text.toString().toDoubleOrNull()
        val t = binding.edtMruvVfT.text.toString().toDoubleOrNull()
        if (v0 == null || a == null || t == null) { toast("Preencha todos os campos"); return }
        val v = v0 + a * t
        binding.tvResultMruvVf.text = "v = %.4f m/s".format(v)
        binding.tvResultMruvVf.visibility = View.VISIBLE
        MainActivity.addHistorico("MRUV velocidade: v = %.4f m/s".format(v))
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}