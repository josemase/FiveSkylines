package com.example.carreracontrahambre

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.example.carreracontrahambre.carrera.CarreraFragment
import com.example.carreracontrahambre.estudiante.Estudiante
import com.example.carreracontrahambre.patrocinador.Patrocinador
import com.example.carreracontrahambre.profesor.Profesor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        database = Firebase.database.reference
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var email: String
        var pass: String
        btn_cambiarUsuario.setOnClickListener() {
            if (constraintLayout2.visibility == VISIBLE) {
                constraintLayout2.visibility = INVISIBLE
                constraintLayout3.visibility = VISIBLE
                constraintLayout4.visibility = INVISIBLE
            }
            else if (constraintLayout3.visibility == VISIBLE) {
                constraintLayout2.visibility = INVISIBLE
                constraintLayout3.visibility = INVISIBLE
                constraintLayout4.visibility = VISIBLE
            }
            else if (constraintLayout4.visibility == VISIBLE) {
                constraintLayout2.visibility = VISIBLE
                constraintLayout3.visibility = INVISIBLE
                constraintLayout4.visibility = INVISIBLE
            }
        }

        btn_registroEst.setOnClickListener() {
            email = editTextEmailEst.text.toString()
            pass = editTextPasswordEst.text.toString()
            registerEst(email, pass)

        }
        buttonRegister.setOnClickListener() {
            email = editTextEmail.text.toString()
            pass = editTextPassword.text.toString()
            registerProf(email, pass)

        }
        buttonRegisterPat.setOnClickListener() {
            email = editTextEmailPat.text.toString()
            pass = editTextPasswordPat.text.toString()
            registerPat(email, pass)

        }
    }


    private fun registerEst(email: String, pass: String) {
        Log.d(TAG, "registerEst:$email")

        if (!validateForm()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(activity as Activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    writeNewUser(
                        editTextNameEst.text.toString(),
                        editTextEmailEst.text.toString(),
                        editTextGradeEst.text.toString(),
                        editTextColegio.text.toString()
                    )
                    Toast.makeText(context, "U Signed Up successfully", Toast.LENGTH_LONG).show();
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, CarreraFragment())
                        .commit()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // ...
            }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = editTextEmailEst.text.toString()
        if (TextUtils.isEmpty(email)) {
            editTextEmailEst.error = "Required."
            valid = false
        } else {
            editTextEmailEst.error = null
        }

        val password = editTextPasswordEst.text.toString()
        if (TextUtils.isEmpty(password)) {
            editTextPasswordEst.error = "Required."
            valid = false
        } else {
            editTextPasswordEst.error = null
        }

        return valid
    }

    private fun writeNewUser(name: String, email: String, grade: String, school: String) {
        val estu = Estudiante(name, grade, school)
        val em = email.split("@")
        val e = em[1].split(".")
        var x = em[0] + concatx(e)
        database.child("Estudiante").child(x).setValue(estu)
    }

    private fun registerProf(email: String, pass: String) {
        Log.d(TAG, "registerProf:$email")

        if (!validateFormProf()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(activity as Activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    writeNewUser(
                        editTextColegio.text.toString(),
                        editTextEmail.text.toString(),
                        editTextGradeProf.text.toString(),
                        editTextSubject.text.toString(),
                        editTextNameProf.text.toString()
                    )
                    Toast.makeText(context, "U Signed Up successfully", Toast.LENGTH_LONG).show();
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, CarreraFragment())
                        .commit()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // ...
            }
    }

    private fun validateFormProf(): Boolean {
        var valid = true

        val email = editTextEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            editTextEmail.error = "Required."
            valid = false
        } else {
            editTextEmail.error = null
        }

        val password = editTextPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            editTextPassword.error = "Required."
            valid = false
        } else {
            editTextPassword.error = null
        }

        return valid
    }

    private fun writeNewUser(
        school: String,
        email: String,
        grade: String,
        subject: String,
        name: String
    ) {
        val prof = Profesor(name, school, grade, subject)
        val em = email.split("@")
        val e = em[1].split(".")
        var x = em[0] + concatx(e)
        database.child("Profesor").child(x).setValue(prof)
    }


    private fun registerPat(email: String, pass: String) {
        Log.d(TAG, "registerPat:$email")

        if (!validateFormPat()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(activity as Activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    writeNewPat(
                        editTextNamePat.text.toString(),
                        editTextEmailPat.text.toString(),
                        editTextPromesa.text.toString(),
                    )
                    Toast.makeText(context, "U Signed Up successfully", Toast.LENGTH_LONG).show();
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, CarreraFragment())
                        .commit()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // ...
            }
    }

    private fun validateFormPat(): Boolean {
        var valid = true

        val email = editTextEmailPat.text.toString()
        if (TextUtils.isEmpty(email)) {
            editTextEmailPat.error = "Required."
            valid = false
        } else {
            editTextEmailPat.error = null
        }

        val password = editTextPasswordPat.text.toString()
        if (TextUtils.isEmpty(password)) {
            editTextPasswordPat.error = "Required."
            valid = false
        } else {
            editTextPasswordPat.error = null
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
    private fun writeNewPat(name: String, email: String, promise: String) {
        val pat = Patrocinador(name, promise)
        val em = email.split("@")
        val e = em[1].split(".")
        var x = em[0] + concatx(e)
        database.child("Patrocinador").child(x).setValue(pat)
    }

}