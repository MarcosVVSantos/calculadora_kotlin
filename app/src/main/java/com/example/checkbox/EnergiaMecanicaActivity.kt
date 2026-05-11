package com.example.checkbox

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class EnergiaMecanicaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_energia_mecanica)

        val edtMassa = findViewById<EditText>(R.id.edtMassa)
        val edtAltura = findViewById<EditText>(R.id.edtAltura)
        val edtVelocidade = findViewById<EditText>(R.id.edtVelocidade)

        val checkEp = findViewById<CheckBox>(R.id.checkEp)
        val checkEc = findViewById<CheckBox>(R.id.checkEc)
        val checkEm = findViewById<CheckBox>(R.id.checkEm)

        val btnCalcular = findViewById<Button>(R.id.btnCalcularEnergia)
        val txtResultado = findViewById<TextView>(R.id.txtResultadoEnergia)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarMenu)

        btnCalcular.setOnClickListener {

            val massaTexto = edtMassa.text.toString()
            val alturaTexto = edtAltura.text.toString()
            val velocidadeTexto = edtVelocidade.text.toString()

            if (
                massaTexto.isEmpty() ||
                alturaTexto.isEmpty() ||
                velocidadeTexto.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val m = massaTexto.toDouble()
            val h = alturaTexto.toDouble()
            val v = velocidadeTexto.toDouble()

            val g = 9.8

            var resultado = ""

            val ep = m * g * h

            val ec = 0.5 * m * v.pow(2)

            if (checkEp.isChecked) {
                resultado += "Energia Potencial: %.2f J\n".format(ep)
            }

            if (checkEc.isChecked) {
                resultado += "Energia Cinética: %.2f J\n".format(ec)
            }

            if (checkEm.isChecked) {

                val em = ep + ec

                resultado += "Energia Mecânica: %.2f J\n".format(em)
            }

            txtResultado.text = resultado
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}