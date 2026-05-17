package com.example.checkbox

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.checkbox.databinding.ActivityGeometriaPlanaBinding
import kotlin.math.PI

class GeometriaPlanaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGeometriaPlanaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeometriaPlanaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.geo_plana_titulo)
        }

        binding.btnCalcQuad.setOnClickListener { calcularQuadrado() }
        binding.btnLimparQuad.setOnClickListener {
            binding.edtQuadLado.setText("")
            binding.tvResultQuad.visibility = View.GONE
        }

        binding.btnCalcRet.setOnClickListener { calcularRetangulo() }
        binding.btnLimparRet.setOnClickListener {
            binding.edtRetBase.setText("")
            binding.edtRetAltura.setText("")
            binding.tvResultRet.visibility = View.GONE
        }

        binding.btnCalcTri.setOnClickListener { calcularTriangulo() }
        binding.btnLimparTri.setOnClickListener {
            binding.edtTriBase.setText("")
            binding.edtTriAltura.setText("")
            binding.edtTriLadoA.setText("")
            binding.edtTriLadoC.setText("")
            binding.tvResultTri.visibility = View.GONE
        }

        binding.btnCalcCirc.setOnClickListener { calcularCirculo() }
        binding.btnLimparCirc.setOnClickListener {
            binding.edtCircRaio.setText("")
            binding.tvResultCirc.visibility = View.GONE
        }
    }

    private fun calcularQuadrado() {
        val l = binding.edtQuadLado.text.toString().toDoubleOrNull()
        if (l == null || l <= 0) { toast("Digite um lado válido"); return }
        val a = l * l
        val p = 4 * l
        binding.tvResultQuad.text = "Área = %.4f u²\nPerímetro = %.4f u".format(a, p)
        binding.tvResultQuad.visibility = View.VISIBLE
        MainActivity.addHistorico("Quadrado: A=%.4f, P=%.4f".format(a, p))
    }

    private fun calcularRetangulo() {
        val b = binding.edtRetBase.text.toString().toDoubleOrNull()
        val h = binding.edtRetAltura.text.toString().toDoubleOrNull()
        if (b == null || h == null || b <= 0 || h <= 0) { toast("Digite valores válidos"); return }
        val a = b * h
        val p = 2 * (b + h)
        binding.tvResultRet.text = "Área = %.4f u²\nPerímetro = %.4f u".format(a, p)
        binding.tvResultRet.visibility = View.VISIBLE
        MainActivity.addHistorico("Retângulo: A=%.4f, P=%.4f".format(a, p))
    }

    private fun calcularTriangulo() {
        val base = binding.edtTriBase.text.toString().toDoubleOrNull()
        val h = binding.edtTriAltura.text.toString().toDoubleOrNull()
        val a = binding.edtTriLadoA.text.toString().toDoubleOrNull()
        val c = binding.edtTriLadoC.text.toString().toDoubleOrNull()

        if (base == null || h == null) { toast("Base e altura são obrigatórios para a área"); return }
        if (base <= 0 || h <= 0) { toast("Digite valores positivos"); return }

        val area = (base * h) / 2.0
        val sb = StringBuilder("Área = %.4f u²".format(area))

        if (a != null && c != null && a > 0 && c > 0) {
            val perim = a + base + c
            sb.append("\nPerímetro = %.4f u".format(perim))
        } else {
            sb.append("\n(Informe os 3 lados para o perímetro)")
        }

        binding.tvResultTri.text = sb.toString()
        binding.tvResultTri.visibility = View.VISIBLE
        MainActivity.addHistorico("Triângulo: A=%.4f u²".format(area))
    }

    private fun calcularCirculo() {
        val r = binding.edtCircRaio.text.toString().toDoubleOrNull()
        if (r == null || r <= 0) { toast("Digite um raio válido"); return }
        val area = PI * r * r
        val circ = 2 * PI * r
        binding.tvResultCirc.text = "Área = %.4f u²\nCircunferência = %.4f u".format(area, circ)
        binding.tvResultCirc.visibility = View.VISIBLE
        MainActivity.addHistorico("Círculo: A=%.4f u²".format(area))
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}