package com.example.carreracontrahambre.carrera

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import com.example.carreracontrahambre.R
import kotlinx.android.synthetic.main.fragment_carrera.*

class CarreraFragment : Fragment(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var corriendo= false
    private var pasosTotales= 0f
    private var pasostotalesAnter= 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        corriendo=true
        val stepSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if(stepSensor==null){

            Toast.makeText(context, "No hay sensor de pasos",Toast.LENGTH_LONG).show()
        }else{

            sensorManager?.registerListener(this, stepSensor,SensorManager.SENSOR_DELAY_UI)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carrera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(corriendo){

            pasosTotales=event!!.values[0]
            val pasoActuales= pasosTotales.toInt()-pasostotalesAnter.toInt()
            vueltasCanti.text= ("$pasoActuales")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}