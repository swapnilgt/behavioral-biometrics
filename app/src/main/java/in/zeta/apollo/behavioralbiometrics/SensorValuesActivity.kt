package `in`.zeta.apollo.behavioralbiometrics

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SensorValuesActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var labelLight: TextView
    private lateinit var labelProximity: TextView
    private lateinit var labelAccelerometer: TextView
    private lateinit var labelGravity: TextView
    private lateinit var labelGyroscope: TextView
    private lateinit var labelLinearAccel: TextView
    private lateinit var labelRotationVect: TextView
    private lateinit var labelAmbTemperature: TextView

    // Individual light and proximity sensors.
    private var mSensorProximity: Sensor? = null
    private var mSensorLight: Sensor? = null
    private var mSensorAccelerometer: Sensor? = null
    private var mSensorGravity: Sensor? = null
    private var mSensorGyroscope: Sensor? = null
    private var mSensorLinelAccel: Sensor? = null
    private var mSensorRotationVect: Sensor? = null
    private var mSensorTemperature: Sensor? = null

    private lateinit var mSensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor_values)

        setupView()

        setupSensors()
    }

    override fun onStop() {
        super.onStop()
        mSensorManager.unregisterListener(this)
    }

    private fun setupView() {
        labelLight = findViewById(R.id.label_light)
        labelProximity = findViewById(R.id.label_proximity)
        labelAccelerometer = findViewById(R.id.label_accel)
        labelGravity = findViewById(R.id.label_gravity)
        labelGyroscope = findViewById(R.id.label_gyroscope)
        labelLinearAccel = findViewById(R.id.label_linear_accel)
        labelRotationVect = findViewById(R.id.label_rotation_vector)
        labelAmbTemperature = findViewById(R.id.label_temperatue)
    }

    private fun setupSensors() {

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mSensorGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
        mSensorGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        mSensorLinelAccel = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        mSensorRotationVect = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        mSensorTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
    }

    override fun onStart() {
        super.onStart()

        mSensorTemperature?.let {
            mSensorManager.registerListener(
                this,
                mSensorTemperature,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } ?: let { labelLight.text = getString(R.string.error_no_sensor) }

        mSensorProximity?.let{
            mSensorManager.registerListener(
                this, mSensorProximity,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } ?: let { labelProximity.text = getString(R.string.error_no_sensor) }

        mSensorLight?.let{
            mSensorManager.registerListener(
                this, mSensorLight,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } ?: let { labelLight.text = getString(R.string.error_no_sensor) }

        mSensorAccelerometer?.let{
            mSensorManager.registerListener(
                this, mSensorAccelerometer,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } ?: let { labelLight.text = getString(R.string.error_no_sensor) }

        mSensorGravity?.let{
            mSensorManager.registerListener(
                this, mSensorGravity,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } ?: let { labelLight.text = getString(R.string.error_no_sensor) }

        mSensorGyroscope?.let{
            mSensorManager.registerListener(
                this, mSensorGyroscope,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } ?: let { labelLight.text = getString(R.string.error_no_sensor) }

        mSensorLinelAccel?.let{
            mSensorManager.registerListener(
                this, mSensorLinelAccel,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } ?: let { labelLight.text = getString(R.string.error_no_sensor) }

        mSensorLinelAccel?.let{
            mSensorManager.registerListener(
                this, mSensorLinelAccel,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } ?: let { labelLight.text = getString(R.string.error_no_sensor) }
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        sensorEvent?.let {
            when(it.sensor.type) {
                Sensor.TYPE_AMBIENT_TEMPERATURE -> {
                    labelAmbTemperature.text = resources.getString(R.string.label_temperature, it.values[0])
                }
                Sensor.TYPE_LIGHT -> {
                    labelLight.text = resources.getString(R.string.label_light, it.values[0])
                }
                Sensor.TYPE_PROXIMITY ->  {
                    labelProximity.text = resources.getString(R.string.label_proximity, it.values[0])
                }
                Sensor.TYPE_ACCELEROMETER -> {
                    labelAccelerometer.text =
                        resources.getString(R.string.label_accelerometer, it.values[0], it.values[1], it.values[2])
                }
                Sensor.TYPE_GRAVITY -> {
                    labelGravity.text =
                        resources.getString(R.string.label_gravity, it.values[0], it.values[1], it.values[2])
                }
                Sensor.TYPE_GYROSCOPE -> {
                    labelGyroscope.text =
                        resources.getString(R.string.label_gyroscope, it.values[0], it.values[1], it.values[2])
                }
                Sensor.TYPE_LINEAR_ACCELERATION -> {
                    labelLinearAccel.text =
                        resources.getString(R.string.label_linear_acceleration, it.values[0], it.values[1], it.values[2])
                }
                Sensor.TYPE_ROTATION_VECTOR -> {
                    labelRotationVect.text =
                        resources.getString(R.string.label_rotation_vector, it.values[0], it.values[1], it.values[2])
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, p1: Int) {
        //
    }
}