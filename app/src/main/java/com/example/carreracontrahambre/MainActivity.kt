package com.example.carreracontrahambre

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.example.carreracontrahambre.carrera.CarreraFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CarreraFragment())
            .commit()
        return super.onCreateView(name, context, attrs)
    }

}