package com.seba.malosh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class MetasFragment : Fragment() {

    private lateinit var volverButton: Button
    private lateinit var definirMetasButton: Button
    private lateinit var habitosLayout: LinearLayout

    companion object {
        private const val REGISTERED_HABITS_KEY = "registered_habits"

        // Método para instanciar el fragmento con los hábitos registrados
        fun newInstance(registeredHabits: ArrayList<String>): MetasFragment {
            val fragment = MetasFragment()
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
        val view = inflater.inflate(R.layout.fragment_metas, container, false)

        volverButton = view.findViewById(R.id.volverButton)
        definirMetasButton = view.findViewById(R.id.definirMetasButton)
        habitosLayout = view.findViewById(R.id.habitosLayout)

        // Obtener los malos hábitos registrados desde los argumentos
        val registeredHabits = arguments?.getStringArrayList(REGISTERED_HABITS_KEY)

        if (registeredHabits.isNullOrEmpty()) {
            // Mostrar mensaje si no hay malos hábitos registrados
            val mensaje = TextView(context)
            mensaje.text = "Aún no tienes malos hábitos registrados."
            habitosLayout.addView(mensaje)
        } else {
            // Mostrar los malos hábitos registrados
            registeredHabits.forEach { habito ->
                val textView = TextView(context)
                textView.text = habito
                textView.setPadding(16, 16, 16, 16)
                habitosLayout.addView(textView)
            }
        }

        // Acción para el botón "Volver"
        volverButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }
}
