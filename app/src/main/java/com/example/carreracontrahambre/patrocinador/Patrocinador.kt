package com.example.carreracontrahambre.patrocinador

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Patrocinador(

    var nombre: String? = "",
    var promesa: String? = ""
    
)