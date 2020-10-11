package com.example.carreracontrahambre

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carreracontrahambre.carrera.CarreraFragment
import kotlinx.android.synthetic.main.fragment_estudiante_inicio.*


class EstudianteInicioFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estudiante_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sensibili.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SensibilizacionFragment())
                .addToBackStack("toSensi")
                .commit()
        }
        carrera.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, CarreraFragment())
                .addToBackStack("toCarrera")
                .commit()
        }

        patrocinadores.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, InscribePatrocinaFragment())
                .addToBackStack("toPatrocina")
                .commit()
        }
    }


}