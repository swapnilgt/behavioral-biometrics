package `in`.zeta.apollo.behavioralbiometrics

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {


    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var button: Button
    private lateinit var sensorListView: TextView

    private var mSensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()

        setupSensorService()

        button.setOnClickListener {
            val i = Intent(this, SensorValuesActivity::class.java)
            startActivity(i)
        }

    }

    private fun setupView() {
        sensorListView = findViewById(R.id.sensor_list)
        button = findViewById(R.id.button)
    }

    private fun setupSensorService() {

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensorList = mSensorManager?.getSensorList(Sensor.TYPE_ALL)

        val sensorText = StringBuilder()

        sensorList?.let {
            for (currentSensor in it) {
                sensorText.append(currentSensor.name).append(
                    System.getProperty("line.separator")
                )
            }
        } ?: run {
            Toast.makeText(this, "Sensors list is empty", Toast.LENGTH_LONG).show()
        }

        sensorListView.text = sensorText

    }


    // Below code for touch events ..

    /*override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        button.dispatchTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }*/

    /*override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            Log.d(TAG, "event: ${
                when(it.action) {
                    0 -> "DOWN"        
                    1 -> "UP"        
                    2 -> "MOVE"
                    else -> "NON HANDLED EVENT"
                }
            }")
            Log.d(TAG, "event pressure: ${it.pressure}")
            Log.d(TAG, "x position: ${it.x}")
            Log.d(TAG, "y position: ${it.y}")
            Log.d(TAG, "area : ${it.size}")
            Log.d(TAG, "************************")
        }
        return super.onTouchEvent(event)
    }*/
}