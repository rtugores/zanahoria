package huitca1212.cuantotemide

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.Toast

class MainActivity : OptionsActivity(), View.OnClickListener {

    private var countrySpinner: Spinner? = null
    private var startButton: Button? = null
    private val dataArray = arrayOf(
        "Selecciona país...",
        "Andorra",
        "Argentina",
        "Belice",
        "Bolivia",
        "Brasil",
        "Canadá",
        "Chile",
        "Colombia",
        "Costa Rica",
        "Cuba",
        "Ecuador",
        "El Salvador",
        "España",
        "Estados Unidos",
        "Francia",
        "Guatemala",
        "Guinea Ecuatorial",
        "Haití",
        "Honduras",
        "Marruecos",
        "México",
        "Nicaragua",
        "OTRO",
        "Panamá",
        "Paraguay",
        "Perú",
        "Puerto Rico",
        "República Dominicana",
        "Trinidad y Tobago",
        "Uruguay",
        "Venezuela"
    )
    private var countrySelected = dataArray[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countrySpinner = findViewById(R.id.countrySpinner)
        startButton = findViewById(R.id.startButton)
        startButton!!.setOnClickListener(this)
        setCountrySpinner()
    }

    private fun setCountrySpinner() {
        val adapter: SpinnerAdapter = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_dropdown_item, dataArray)
        countrySpinner!!.adapter = adapter
        countrySpinner!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                countrySelected = dataArray[i]
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.startButton) {
            onStartButtonClicked()
        }
    }

    private fun onStartButtonClicked() {
        if (countrySelected == dataArray[0]) {
            Toast.makeText(this, "¡Selecciona país!", Toast.LENGTH_SHORT).show()
        } else {
            QuestionsActivity.startActivity(this@MainActivity, countrySelected)
        }
    }

    companion object {

        @JvmStatic fun startActivity(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
