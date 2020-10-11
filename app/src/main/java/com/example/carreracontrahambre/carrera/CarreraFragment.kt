package com.example.carreracontrahambre.carrera

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.carreracontrahambre.FinalizarCarreraFragment
import com.example.carreracontrahambre.Login.LoginFragment
import com.example.carreracontrahambre.R
import kotlinx.android.synthetic.main.fragment_carrera.*
import kotlinx.android.synthetic.main.fragment_carrera.btn_iniciar
import kotlinx.android.synthetic.main.fragment_home.*

class CarreraFragment : Fragment(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var corriendo= false
    private var pasosIni=0
    private var pasosTotales= 0f
    private var pasostotalesAnter= 0f
    private val metrosPorVuelta=300.0
    private var metrosActuales=0
    private var altura= 0
  val viewModel: CarreraViewModel by activityViewModels()
    private var vueltas=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
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

        btn_iniciar.setOnClickListener {
            corriendo=true
            altura = alturaCanti.text.toString().toInt()
        }

        btn_pausar.setOnClickListener {
            corriendo=false
        }

        btn_finalizar.setOnClickListener {
            corriendo=false
           btn_iniciar.visibility=View.INVISIBLE
            btn_pausar.visibility=View.INVISIBLE

            btn_iniciar.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, FinalizarCarreraFragment())
                    .addToBackStack("toDineroPatrocina")
                    .commit()
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(corriendo){
            if(pasosIni==0){
                pasosIni= event!!.values[0].toInt()
            }
            pasosTotales= event!!.values[0]-pasosIni
            val pasosActuales= pasosTotales.toInt()-pasostotalesAnter.toInt()
            Log.e("pasosActuales",pasosActuales.toString() )
            //metrosActuales= calcularMetros(pasosActuales).toInt()
            metrosActuales=250+pasosActuales
            Log.e("metros",metrosActuales.toString() )
            val porcentajeDeVuelta= (metrosActuales*100.0)/metrosPorVuelta
            Log.e("porcentajeVuelta",porcentajeDeVuelta.toString() )
            progresoVuelta.progress=porcentajeDeVuelta.toInt()
            if(metrosActuales.toDouble() >= metrosPorVuelta && metrosActuales.toDouble() <= metrosPorVuelta+5){
                vueltas++
                vueltasCanti.text=vueltas.toString()
                viewModel.setMetrosReco(metrosActuales)
                metrosActuales=0
                progresoVuelta.progress=0
              viewModel.setVueltas(vueltas)
            }
        }
    }
    fun calcularMetros(pasosActuales: Int): Double=pasosActuales*0.414*(altura/100)

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}