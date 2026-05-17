package com.example.checkbox

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.checkbox.databinding.ActivityFisicaMenuBinding

class FisicaMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFisicaMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFisicaMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.fisica_menu_titulo)
        }

        binding.btnCinematica.setOnClickListener {
            startActivity(Intent(this, CinematicaActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.btnDinamica.setOnClickListener {
            startActivity(Intent(this, DinamicaActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.btnEnergia.setOnClickListener {
            startActivity(Intent(this, EnergiaMecanicaActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.btnTermologia.setOnClickListener {
            startActivity(Intent(this, TermologiaActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.btnOptica.setOnClickListener {
            startActivity(Intent(this, OpticaActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}