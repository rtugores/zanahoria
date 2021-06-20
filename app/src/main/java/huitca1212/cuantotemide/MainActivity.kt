package huitca1212.cuantotemide

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import huitca1212.cuantotemide.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val countriesList = listOf(
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
    private var countrySelected = countriesList[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.bind(findViewById(R.id.homeMainContainer))
        setSupportActionBar(binding.appTopBarLayout.appTopBar)

        binding.welcomeTextView.text = htmlToPlainString(getString(R.string.welcome_text))
        binding.startButton.setOnClickListener { onStartButtonClicked() }
        setCountrySpinner()
    }

    private fun setCountrySpinner() {
        val adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_dropdown_item, countriesList)
        binding.countrySpinner.adapter = adapter
        binding.countrySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                countrySelected = countriesList[position]
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) = Unit
        }
    }

    private fun onStartButtonClicked() {
        if (countrySelected == countriesList[0]) {
            Toast.makeText(this, "¡Selecciona país!", Toast.LENGTH_SHORT).show()
        } else {
            QuestionsActivity.startActivity(this@MainActivity, countrySelected)
        }
    }

    private fun htmlToPlainString(source: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(source)
        }.toString()
    }

    companion object {

        fun startActivity(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
