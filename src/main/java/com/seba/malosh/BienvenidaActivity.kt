package com.seba.malosh

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction

class BienvenidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)

        // Configurar la Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Menú"
    }

    // Inflar el menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_navegacion, menu)
        return true
    }

    // Manejar las acciones del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_registrar_habitos -> {
                // Cargar el fragmento de selección de malos hábitos
                val fragment = SeleccionarHabitosFragment()
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
                true
            }
            R.id.menu_item_logout -> {
                // Redirigir al login cuando se presione "Cerrar Sesión"
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Cerrar la actividad para que no se pueda volver con el botón "atrás"
                true
            }
            // Otras opciones del menú
            else -> super.onOptionsItemSelected(item)
        }
    }
}
