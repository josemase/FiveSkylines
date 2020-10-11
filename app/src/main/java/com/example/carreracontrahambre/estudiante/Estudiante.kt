package com.example.carreracontrahambre.estudiante

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Estudiante(
    var correo: String?="",
    var colegio: String? = "",
    var grado: String? = "",
    var nombre: String? = ""
    
)