package com.example.carreracontrahambre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carreracontrahambre.carrera.CarreraFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CarreraFragment())
            .commit()
    }


}