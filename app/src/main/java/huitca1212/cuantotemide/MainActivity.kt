package huitca1212.cuantotemide

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import huitca1212.cuantotemide.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val countries: List<String> by lazy { getCountriesList() }
    private var countrySelected : String? = null

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
        val adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_dropdown_item, countries)
        binding.countrySpinner.adapter = adapter
        binding.countrySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                countrySelected = countries[position]
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) = Unit
        }
    }

    private fun onStartButtonClicked() {
        if (countrySelected == countries.first()) {
            Toast.makeText(this, getString(R.string.welcome_chooser_error), Toast.LENGTH_SHORT).show()
        } else {
            QuestionsActivity.startActivity(this, countrySelected)
        }
    }

    private fun htmlToPlainString(source: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(source)
        }.toString()
    }

    private fun getCountriesList(): List<String> {
        return listOf(
            getString(R.string.welcome_chooser_text),
            getString(R.string.welcome_country_andorra),
            getString(R.string.welcome_country_argentina),
            getString(R.string.welcome_country_belice),
            getString(R.string.welcome_country_bolivia),
            getString(R.string.welcome_country_brasil),
            getString(R.string.welcome_country_canadá),
            getString(R.string.welcome_country_chile),
            getString(R.string.welcome_country_colombia),
            getString(R.string.welcome_country_costa_rica),
            getString(R.string.welcome_country_cuba),
            getString(R.string.welcome_country_ecuador),
            getString(R.string.welcome_country_el_salvador),
            getString(R.string.welcome_country_espanha),
            getString(R.string.welcome_country_estados_unidos),
            getString(R.string.welcome_country_francia),
            getString(R.string.welcome_country_guatemala),
            getString(R.string.welcome_country_guinea_ecuatorial),
            getString(R.string.welcome_country_haití),
            getString(R.string.welcome_country_honduras),
            getString(R.string.welcome_country_marruecos),
            getString(R.string.welcome_country_méxico),
            getString(R.string.welcome_country_nicaragua),
            getString(R.string.welcome_country_otro),
            getString(R.string.welcome_country_panamá),
            getString(R.string.welcome_country_paraguay),
            getString(R.string.welcome_country_perú),
            getString(R.string.welcome_country_puerto_rico),
            getString(R.string.welcome_country_republica_dominicana),
            getString(R.string.welcome_country_trinidad_y_tobago),
            getString(R.string.welcome_country_uruguay),
            getString(R.string.welcome_country_venezuela)
        )
    }

    companion object {

        fun startActivity(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
