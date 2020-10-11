package com.example.carreracontrahambre.Login

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import com.example.carreracontrahambre.MainActivity
import com.example.carreracontrahambre.R
import com.example.carreracontrahambre.carrera.CarreraFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*


class LoginFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var  auth: FirebaseAuth;
    private lateinit var binding: MainActivity
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
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


        private fun logIn(email: String, password: String) {
            Log.d(TAG, "logIn:$email")
            if (!validateForm()) {
                return
            }

            // [START sign_in_with_email]
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity as Activity) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(context,"U Logged In successfully",Toast.LENGTH_LONG).show();
                        activity!!.supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, CarreraFragment())
                            .commit()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(context,"Authentication Failed",Toast.LENGTH_LONG).show();

                    }

                }
            // [END sign_in_with_email]

    }
    private fun validateForm(): Boolean {
        var valid = true

        val email = editTextEmailLogIn.text.toString()
        if (TextUtils.isEmpty(email)) {
            editTextEmail.error = "Required."
            valid = false
        } else {
            editTextEmailLogIn.error = null
        }

        val password = editTextPasswordLogIn.text.toString()
        if (TextUtils.isEmpty(password)) {
            editTextPassword.error = "Required."
            valid = false
        } else {
            editTextPasswordLogIn.error = null
        }

        return valid
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var email: String
        var pass: String

        buttonLogIn.setOnClickListener() {
            email = editTextEmailLogIn.text.toString()
            pass = editTextPasswordLogIn.text.toString()
            logIn(email, pass)

        }
    }
}