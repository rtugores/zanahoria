package huitca1212.cuantotemide

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import huitca1212.cuantotemide.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val getCountriesUseCase by lazy { GetCountriesUseCase(this) }
    private val countries: List<Country> by lazy { getCountriesUseCase.getCountriesWithDefault() }
    private var countrySelected: Country? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.bind(findViewById(R.id.mainMainContainer))
        setSupportActionBar(binding.mainAppTopBarLayout.appTopBar)

        binding.mainWelcomeTextView.text = htmlToPlainString(getString(R.string.welcome_text))
        binding.mainStartButton.setOnClickListener { onStartButtonClicked() }
        setCountrySpinner()
    }

    private fun setCountrySpinner() {
        val countryNames = countries.map { it.name }
        val adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_dropdown_item, countryNames)
        binding.mainCountrySpinner.adapter = adapter
        binding.mainCountrySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                countrySelected = countries[position]
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) = Unit
        }
    }

    private fun onStartButtonClicked() {
        val userName = binding.mainNameEditText.text.toString()
        countrySelected?.code?.let { countryCode ->
            QuestionsActivity.startActivity(activity = this, countryCode =  countryCode, userName =  userName)
        } ?: Toast.makeText(this, getString(R.string.welcome_chooser_error), Toast.LENGTH_SHORT).show()
    }
}

