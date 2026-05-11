package com.example.checkbox

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*

class TrigonometriaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trigonometria)

        val edtAngulo = findViewById<EditText>(R.id.edtAngulo)

        val checkSeno = findViewById<CheckBox>(R.id.checkSeno)
        val checkCosseno = findViewById<CheckBox>(R.id.checkCosseno)
        val checkTangente = findViewById<CheckBox>(R.id.checkTangente)

        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarMenu)

        btnCalcular.setOnClickListener {

            val texto = edtAngulo.text.toString()

            if (texto.isEmpty()) {
                Toast.makeText(this, "Digite um ângulo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val anguloGraus = texto.toDouble()

            val anguloRad = Math.toRadians(anguloGraus)

            var resultado = ""

            if (checkSeno.isChecked) {
                resultado += "Seno: %.4f\n".format(sin(anguloRad))
            }

            if (checkCosseno.isChecked) {
                resultado += "Cosseno: %.4f\n".format(cos(anguloRad))
            }

            if (checkTangente.isChecked) {
                resultado += "Tangente: %.4f\n".format(tan(anguloRad))
            }

            txtResultado.text = resultado
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}