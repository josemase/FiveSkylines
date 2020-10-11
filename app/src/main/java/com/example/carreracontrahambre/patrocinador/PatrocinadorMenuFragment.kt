package com.example.carreracontrahambre.patrocinador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carreracontrahambre.R
import com.example.carreracontrahambre.carrera.CarreraFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_patrocinador_menu.*

private lateinit var database: DatabaseReference
private lateinit var  auth: FirebaseAuth;
class PatrocinadorMenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patrocinador_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonAnadirEstudiantes.setOnClickListener() {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AnadirEstFragment())
                .commit()
        }
    }
}