package com.magicworld.randplays.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.magicworld.randplays.MainActivity
import com.magicworld.randplays.R
import com.magicworld.randplays.databinding.MainFragmentBinding
import com.magicworld.randplays.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mainBinding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).hideIcon()
        mainBinding = MainFragmentBinding.inflate(inflater, container, false)
        return mainBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(mainBinding) {

            guardarNombreButton.setOnClickListener {
                if (nombreTextInputLayout.text.toString().isEmpty())
                    Toast.makeText(context, "Debes escribir tu nombre participante ", Toast.LENGTH_SHORT)
                        .show()
                else {
                    viewModel.guardarNombre(nombreTextInputLayout.text.toString())
                    Toast.makeText(context, "Participante guardado con exito ", Toast.LENGTH_SHORT)
                        .show()
                    nombreTextInputLayout.setText("")
                }
            }

            guardarPenaButton.setOnClickListener {
                if (penaTextImputLayout.text.toString().isEmpty())
                    Toast.makeText(context, "Debes escribir una penitencia", Toast.LENGTH_SHORT)
                        .show()
                else {
                    viewModel.guardarPenitencia(penaTextImputLayout.text.toString())
                    Toast.makeText(context, "Penitencia guardada con exito ", Toast.LENGTH_SHORT)
                        .show()
                    penaTextImputLayout.setText("")
                }
            }

            jugarButton.setOnClickListener {
                viewModel.jugar()

                val builder = AlertDialog.Builder(requireContext())
                val views = layoutInflater.inflate(R.layout.popup, null)
                builder.setView(views)
                val dialog = builder.create()
                dialog.show()

                val nombreGanador: TextView = views.findViewById(R.id.nombre_text_view)
                val penaGanadora: TextView = views.findViewById(R.id.pena_text_view)

                viewModel.onNombreAleatorioDone.observe(viewLifecycleOwner){ nombre ->
                    nombreGanador.text = nombre
                }
                viewModel.onPenaAleatoriaDone.observe(viewLifecycleOwner){pena->
                    penaGanadora.text = pena
                }

            }
        }

    }

}




