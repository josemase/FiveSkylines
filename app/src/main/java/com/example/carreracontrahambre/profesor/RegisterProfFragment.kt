package com.example.carreracontrahambre.profesor

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.carreracontrahambre.R
import com.example.carreracontrahambre.carrera.CarreraFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_register_prof.*

class RegisterProfFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var  auth: FirebaseAuth;
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
        return inflater.inflate(R.layout.fragment_register_prof, container, false)
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

        buttonRegister.setOnClickListener() {
            email = editTextEmail.text.toString()
            pass = editTextPassword.text.toString()
            registerProf(email, pass)

        }
    }


    private fun registerProf(email: String, pass: String){
        Log.d(TAG, "registerProf:$email")

        if (!validateForm()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(activity as Activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    writeNewUser(editTextColegio.text.toString(), editTextEmail.text.toString(),editTextGradeProf.text.toString(),editTextSubject.text.toString(), editTextNameProf.text.toString())
                    Toast.makeText(context,"U Signed Up successfully",Toast.LENGTH_LONG).show();
                    activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, CarreraFragment())
                        .commit()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText( context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

                // ...
            }
    }
    private fun validateForm(): Boolean {
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
    private fun writeNewUser(school: String, email: String, grade: String, subject: String, name: String) {
        val prof = Profesor(school, email, grade, subject, name)

        database.child("Profesor").child("Prof").setValue(prof)
    }

}