package com.example.sublyappv04

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.view.ViewGroup as ViewGroup1

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var selectedColor: String? = null
private var selectedMaterial: String? = null

/**
 * A simple [Fragment] subclass.
 * Use the [CamisasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

interface OnSpinnerItemSelectedListener {
    fun onItemSelected(selectedOption: String)
}


class CamisasFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_camisas, container, false)

        // Llamar a la función crearSpinner para agregar un nuevo Spinner al diseño
        val colores = listOf("Color de fondo", "Blanco", "Negro", "Azul", "Verde", "Rosado", "Naranja")
        crearSpinner(requireContext(), view, colores, R.id.spinnerColores) { selectedOption ->
            Log.d("opc", selectedOption)
            selectedColor =selectedOption
            Log.d("opc", "Guardado$selectedColor")
        }

        val listaMateriales = listOf("Seleccione Material","Algodón", "Poliéster", "Algodón-Poliéster")
        crearSpinner(requireContext(), view, listaMateriales, R.id.spinneMaterialCamisa) { selectedOption ->
            Log.d("opc", selectedOption)
            selectedMaterial = selectedOption
            Log.d("opc", "Guardado$selectedMaterial")
        }

        return view
    }

    // Función para crear un Spinner dinámico y agregarlo al diseño XML
    fun crearSpinner(context: Context, view: View, listaOpciones: List<String>, spinnerId: Int, callback: (String) -> Unit) {
        val spinnerDinamico = view.findViewById<Spinner>(spinnerId)
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, listaOpciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDinamico.adapter = adapter

        spinnerDinamico.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @SuppressLint("SuspiciousIndentation")
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedOption = listaOpciones[position]
                Log.d("opciones", selectedOption)
                callback(selectedOption) // Llama a la función de devolución de llamada
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Se llama cuando no se selecciona ningún elemento.
            }
        }
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CamisasFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CamisasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

