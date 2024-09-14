package com.seba.malosh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment

class SeleccionarHabitosFragment : Fragment() {

    private lateinit var volverButton: Button
    private lateinit var siguienteButton: Button
    private lateinit var checkboxes: List<CheckBox>
    private var registeredHabits = ArrayList<String>()

    companion object {
        private const val REGISTERED_HABITS_KEY = "registered_habits"

        // Método para instanciar el fragmento con los hábitos ya registrados
        fun newInstance(registeredHabits: ArrayList<String>): SeleccionarHabitosFragment {
            val fragment = SeleccionarHabitosFragment()
            val bundle = Bundle()
            bundle.putStringArrayList(REGISTERED_HABITS_KEY, registeredHabits)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_seleccionar_habitos, container, false)

        // Inicializar los elementos del layout
        volverButton = view.findViewById(R.id.volverButton)
        siguienteButton = view.findViewById(R.id.siguienteButton)

        checkboxes = listOf(
            view.findViewById(R.id.checkbox_fumar),
            view.findViewById(R.id.checkbox_alcohol),
            view.findViewById(R.id.checkbox_mala_higiene),
            view.findViewById(R.id.checkbox_cafeina),
            view.findViewById(R.id.checkbox_interrumpir),
            view.findViewById(R.id.checkbox_dormir),
            view.findViewById(R.id.checkbox_comer),
            view.findViewById(R.id.checkbox_no_beber_agua),
            view.findViewById(R.id.checkbox_mala_alimentacion),
            view.findViewById(R.id.checkbox_poco_ejercicio)
        )

        // Obtener los hábitos registrados desde los argumentos
        registeredHabits = arguments?.getStringArrayList(REGISTERED_HABITS_KEY) ?: ArrayList()

        // Deshabilitar los CheckBoxes de los hábitos ya registrados
        checkboxes.forEach { checkbox ->
            if (registeredHabits.contains(checkbox.text.toString())) {
                checkbox.isEnabled = false // Deshabilitar el CheckBox
            }
        }

        // Acción para el botón "Siguiente"
        siguienteButton.setOnClickListener {
            val selectedHabits = checkboxes.filter { it.isChecked }.map { it.text.toString() }

            if (selectedHabits.size + registeredHabits.size < 2) {
                Toast.makeText(context, "Selecciona al menos 2 hábitos", Toast.LENGTH_SHORT).show()
            } else if (selectedHabits.size + registeredHabits.size > 4) {
                // Mostrar mensaje cuando se supera el límite de 4 hábitos
                Toast.makeText(context, "Ya has registrado el máximo de hábitos permitidos", Toast.LENGTH_SHORT).show()
            } else {
                // Pasar al fragmento de confirmación
                val confirmarFragment = ConfirmarHabitosFragment.newInstance(ArrayList(selectedHabits))
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, confirmarFragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }

        // Acción para el botón "Volver"
        volverButton.setOnClickListener {
            // Regresa a la actividad anterior
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }
}
