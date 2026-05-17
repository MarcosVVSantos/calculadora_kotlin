package com.example.checkbox

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.checkbox.databinding.ActivityMatematicaMenuBinding

class MatematicaMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatematicaMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatematicaMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.mat_menu_titulo)
        }

        binding.btnGeoPlana.setOnClickListener {
            startActivity(Intent(this, GeometriaPlanaActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.btnGeoEspacial.setOnClickListener {
            startActivity(Intent(this, GeometriaEspacialActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.btnEquacoes.setOnClickListener {
            startActivity(Intent(this, EquacoesActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.btnTrigonometria.setOnClickListener {
            startActivity(Intent(this, TrigonometriaActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.btnEstatistica.setOnClickListener {
            startActivity(Intent(this, EstatisticaActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}