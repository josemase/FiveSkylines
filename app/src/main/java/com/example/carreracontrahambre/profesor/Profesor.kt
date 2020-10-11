package com.example.carreracontrahambre.profesor

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Profesor(

    var colegio: String? = "",
    var grado: String? = "",
    var materia: String? = "",
    var nombre: String? = ""
    
)