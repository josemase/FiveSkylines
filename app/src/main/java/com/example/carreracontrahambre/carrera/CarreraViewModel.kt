package com.example.carreracontrahambre.carrera

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carreracontrahambre.estudiante.Estudiante
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class CarreraViewModel: ViewModel() {
    private lateinit var database: DatabaseReference


     val metrosRecorri= MutableLiveData<Int>()
    var estudia=MutableLiveData<Estudiante>()
    fun setEstudi(estudiante: Estudiante){
       estudia.value=estudiante
    }

    fun getEstudi(){
        database = Firebase.database.reference
        val referencia= database.child("Patrocinador")
       if(estudia.value != null) {
           val estudi= estudia.value ?: Estudiante()
           var correo = estudi.correo ?: ""
           referencia.orderByChild(correo).orderByKey()
       }
    }

    val postListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            val post = dataSnapshot
            // ...
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.w(TAG, "loadPost:onCancelled", databaseError.toException())

        }
    }



    fun getMetrosRecorri(): LiveData<Int> = metrosRecorri


    val vueltas= MutableLiveData<Int>()
    fun getVueltas(): LiveData<Int> = vueltas

    fun setMetrosReco(metros: Int){
        if( getMetrosRecorri().value!= null) {
            val metrosRecorrido= getMetrosRecorri().value ?: 0
            metrosRecorri.value =metrosRecorrido+metros
        }
    }
    fun setVueltas(vuelta: Int){
        vueltas.value=vuelta
    }




}