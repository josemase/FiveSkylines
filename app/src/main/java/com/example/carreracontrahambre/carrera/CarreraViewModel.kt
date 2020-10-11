package com.example.carreracontrahambre.carrera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarreraViewModel: ViewModel() {
     val metrosRecorri= MutableLiveData<Int>()
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