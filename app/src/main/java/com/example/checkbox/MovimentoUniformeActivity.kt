package com.example.checkbox

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MovimentoUniformeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movimento_uniforme)

        val edtDistancia = findViewById<EditText>(R.id.edtDistancia)
        val edtVelocidade = findViewById<EditText>(R.id.edtVelocidade)
        val edtTempo = findViewById<EditText>(R.id.edtTempo)

        val checkDistancia = findViewById<CheckBox>(R.id.checkDistancia)
        val checkVelocidade = findViewById<CheckBox>(R.id.checkVelocidade)
        val checkTempo = findViewById<CheckBox>(R.id.checkTempo)

        val btnCalcular = findViewById<Button>(R.id.btnCalcularMU)
        val txtResultado = findViewById<TextView>(R.id.txtResultadoMU)

        val btnVoltar = findViewById<Button>(R.id.btnVoltarMenu)

        checkDistancia.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {

                checkVelocidade.isChecked = false
                checkTempo.isChecked = false

                edtDistancia.isEnabled = false

                edtVelocidade.isEnabled = true
                edtTempo.isEnabled = true

                edtDistancia.setText("")
            } else {

                edtDistancia.isEnabled = true
            }
        }

        checkVelocidade.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {

                checkDistancia.isChecked = false
                checkTempo.isChecked = false

                edtVelocidade.isEnabled = false

                edtDistancia.isEnabled = true
                edtTempo.isEnabled = true

                edtVelocidade.setText("")
            } else {

                edtVelocidade.isEnabled = true
            }
        }

        checkTempo.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {

                checkDistancia.isChecked = false
                checkVelocidade.isChecked = false

                edtTempo.isEnabled = false

                edtDistancia.isEnabled = true
                edtVelocidade.isEnabled = true

                edtTempo.setText("")
            } else {

                edtTempo.isEnabled = true
            }
        }

        btnCalcular.setOnClickListener {

            var resultado = ""

            if (
                !checkDistancia.isChecked &&
                !checkVelocidade.isChecked &&
                !checkTempo.isChecked
            ) {

                Toast.makeText(
                    this,
                    "Selecione um cálculo",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (checkDistancia.isChecked) {

                val v = edtVelocidade.text.toString().toDoubleOrNull()
                val t = edtTempo.text.toString().toDoubleOrNull()

                if (v == null || t == null) {

                    Toast.makeText(
                        this,
                        "Preencha velocidade e tempo",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                val d = v * t

                resultado += "Distância = %.2f\n".format(d)
            }

            if (checkVelocidade.isChecked) {

                val d = edtDistancia.text.toString().toDoubleOrNull()
                val t = edtTempo.text.toString().toDoubleOrNull()

                if (d == null || t == null) {

                    Toast.makeText(
                        this,
                        "Preencha distância e tempo",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                if (t == 0.0) {

                    Toast.makeText(
                        this,
                        "Tempo não pode ser zero",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                val v = d / t

                resultado += "Velocidade = %.2f\n".format(v)
            }

            if (checkTempo.isChecked) {

                val d = edtDistancia.text.toString().toDoubleOrNull()
                val v = edtVelocidade.text.toString().toDoubleOrNull()

                if (d == null || v == null) {

                    Toast.makeText(
                        this,
                        "Preencha distância e velocidade",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                if (v == 0.0) {

                    Toast.makeText(
                        this,
                        "Velocidade não pode ser zero",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                val t = d / v

                resultado += "Tempo = %.2f\n".format(t)
            }

            txtResultado.text = resultado
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}