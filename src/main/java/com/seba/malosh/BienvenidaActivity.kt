package com.seba.malosh

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction

class BienvenidaActivity : AppCompatActivity() {

    // Lista de hábitos registrados
    private val registeredHabits = ArrayList<String>()

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
                // Solo permitir registrar hábitos si hay menos de 4 registrados
                if (registeredHabits.size >= 4) {
                    // Mostrar un mensaje diciendo que ya se han registrado el máximo de hábitos
                    Toast.makeText(this, "Ya has registrado el máximo de hábitos permitidos", Toast.LENGTH_SHORT).show()
                } else {
                    // Cargar el fragmento de selección de malos hábitos con los hábitos ya registrados
                    val fragment = SeleccionarHabitosFragment.newInstance(registeredHabits)
                    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragment_container, fragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                true
            }
            R.id.menu_item_metas -> {
                // Cargar el fragmento de metas con los hábitos registrados
                val fragment = MetasFragment.newInstance(registeredHabits) // Pasar la lista de hábitos registrados
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
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Método para actualizar la lista de hábitos registrados
    fun updateRegisteredHabits(newHabits: List<String>) {
        registeredHabits.addAll(newHabits)
    }
}
