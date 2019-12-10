package hu.ait.sensordemo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        btnStart.setOnClickListener {
            val sensorAcc = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

            sensorManager.registerListener(this@MainActivity,
                sensorAcc, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        val accX = sensorEvent.values[0].toDouble()
        val accY = sensorEvent.values[1].toDouble()
        val accZ = sensorEvent.values[2].toDouble()

        tvData.text =
            "X: $accX\nY: $accY\nZ: $accZ"
    }
}
