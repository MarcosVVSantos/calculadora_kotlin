package com.example.checkbox

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.checkbox.databinding.ActivityGeometriaEspacialBinding
import kotlin.math.PI
import kotlin.math.pow

class GeometriaEspacialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGeometriaEspacialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeometriaEspacialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.geo_espacial_titulo)
        }

        binding.btnCalcCubo.setOnClickListener { calcularCubo() }
        binding.btnLimparCubo.setOnClickListener {
            binding.edtCuboAresta.setText("")
            binding.tvResultCubo.visibility = View.GONE
        }

        binding.btnCalcParal.setOnClickListener { calcularParalelepipedo() }
        binding.btnLimparParal.setOnClickListener {
            binding.edtParalA.setText("")
            binding.edtParalB.setText("")
            binding.edtParalC.setText("")
            binding.tvResultParal.visibility = View.GONE
        }

        binding.btnCalcCil.setOnClickListener { calcularCilindro() }
        binding.btnLimparCil.setOnClickListener {
            binding.edtCilRaio.setText("")
            binding.edtCilAltura.setText("")
            binding.tvResultCil.visibility = View.GONE
        }

        binding.btnCalcEsfera.setOnClickListener { calcularEsfera() }
        binding.btnLimparEsfera.setOnClickListener {
            binding.edtEsferaRaio.setText("")
            binding.tvResultEsfera.visibility = View.GONE
        }
    }

    private fun calcularCubo() {
        val a = binding.edtCuboAresta.text.toString().toDoubleOrNull()
        if (a == null || a <= 0) { toast("Digite uma aresta válida"); return }
        val v = a.pow(3)
        val sup = 6 * a.pow(2)
        binding.tvResultCubo.text = "Volume = %.4f u³\nÁrea superficial = %.4f u²".format(v, sup)
        binding.tvResultCubo.visibility = View.VISIBLE
        MainActivity.addHistorico("Cubo: V=%.4f u³".format(v))
    }

    private fun calcularParalelepipedo() {
        val a = binding.edtParalA.text.toString().toDoubleOrNull()
        val b = binding.edtParalB.text.toString().toDoubleOrNull()
        val c = binding.edtParalC.text.toString().toDoubleOrNull()
        if (a == null || b == null || c == null || a <= 0 || b <= 0 || c <= 0) { toast("Preencha todos os campos"); return }
        val v = a * b * c
        binding.tvResultParal.text = "Volume = %.4f u³".format(v)
        binding.tvResultParal.visibility = View.VISIBLE
        MainActivity.addHistorico("Paralelepípedo: V=%.4f u³".format(v))
    }

    private fun calcularCilindro() {
        val r = binding.edtCilRaio.text.toString().toDoubleOrNull()
        val h = binding.edtCilAltura.text.toString().toDoubleOrNull()
        if (r == null || h == null || r <= 0 || h <= 0) { toast("Preencha raio e altura"); return }
        val v = PI * r.pow(2) * h
        binding.tvResultCil.text = "Volume = %.4f u³".format(v)
        binding.tvResultCil.visibility = View.VISIBLE
        MainActivity.addHistorico("Cilindro: V=%.4f u³".format(v))
    }

    private fun calcularEsfera() {
        val r = binding.edtEsferaRaio.text.toString().toDoubleOrNull()
        if (r == null || r <= 0) { toast("Digite um raio válido"); return }
        val v = (4.0 / 3.0) * PI * r.pow(3)
        val a = 4 * PI * r.pow(2)
        binding.tvResultEsfera.text = "Volume = %.4f u³\nÁrea superficial = %.4f u²".format(v, a)
        binding.tvResultEsfera.visibility = View.VISIBLE
        MainActivity.addHistorico("Esfera: V=%.4f u³".format(v))
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}