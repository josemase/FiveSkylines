package com.example.carreracontrahambre.patrocinador

import android.content.ContentValues
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carreracontrahambre.R
import com.example.carreracontrahambre.promesa.Promesa
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_anadir_est.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AnadirEstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnadirEstFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var  auth: FirebaseAuth;
    private var  user: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        database = Firebase.database.reference
        user = auth.currentUser
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anadir_est, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonAnadirEstPat.setOnClickListener(){
            writeNewEstPat(editTextEmailEstPat.text.toString(), editTextPromesaEstPat.text.toString())
        }

    }
    private fun validateForm(): Boolean {
        var valid = true

        val prom = editTextPromesaEstPat.toString()
        if (TextUtils.isEmpty(prom)) {
            editTextPromesaEstPat.error = "Required."
            valid = false
        } else {
            editTextPromesaEstPat.error = null
        }

        val emailEstPat = editTextEmailEstPat.text.toString()
        if (TextUtils.isEmpty(emailEstPat)) {
            editTextEmailEstPat.error = "Required."
            valid = false
        } else {
            editTextEmailEstPat.error = null
        }

        return valid
    }
    private fun concatx(e: List<String>): String {
        var y: String = ""
        for (i: String in e) {
            y += i
        }
        return y
    }
    private fun writeNewEstPat(emailEst: String, promise: String) {
        Log.d(ContentValues.TAG, "logIn:$user")
        if (!validateForm()) {
            return
        }
        val prom = Promesa(promise)
        val em = emailEst.split("@")
        val e = em[1].split(".")
        var x = em[0] + concatx(e)
        var us1: String = user?.email!!
        val em2 = us1.split("@")
        val e2 = em2[1].split(".")
        var x2 = em2[0] + concatx(e2)
        database.child("Patrocinador").child(x2).child(x).setValue(prom)
    }
}