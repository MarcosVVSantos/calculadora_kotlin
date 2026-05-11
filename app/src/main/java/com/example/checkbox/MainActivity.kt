package com.example.checkbox

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnEx1 = findViewById<Button>(R.id.btnEx1)
        val btnEx2 = findViewById<Button>(R.id.btnEx2)
        val btnEx3 = findViewById<Button>(R.id.btnEx3)

        btnEx1.setOnClickListener {
            startActivity(
                Intent(this, TrigonometriaActivity::class.java)
            )
        }
        btnEx2.setOnClickListener {
            startActivity(
                Intent(this, MovimentoUniformeActivity::class.java)
            )
        }
        btnEx3.setOnClickListener {
            startActivity(
                Intent(this, EnergiaMecanicaActivity::class.java)
            )
        }
    }
}