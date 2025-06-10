package huitca1212.cuantotemide

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import huitca1212.cuantotemide.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.bind(findViewById(R.id.mainMainContainer))
        setSupportActionBar(binding.mainAppTopBarLayout.appTopBar)

        binding.mainWelcomeTextView.text = getString(R.string.welcome_text).fromHtml()
        binding.mainStartButton.setOnClickListener { onStartButtonClicked() }

        loadCountriesAndSetupSpinner()
    }

    private fun loadCountriesAndSetupSpinner() {
        lifecycleScope.launch {
            val countries = viewModel.loadCountries()
            val countryNames = countries.map { it.name }
            val adapter = ArrayAdapter<Any?>(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, countryNames)
            binding.mainCountrySpinner.adapter = adapter
            binding.mainCountrySpinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    viewModel.selectCountry(position)
                }

                override fun onNothingSelected(arg0: AdapterView<*>?) = Unit
            }
        }
    }

    private fun onStartButtonClicked() {
        val userName = binding.mainNameEditText.text.toString()
        val country = viewModel.selectedCountry
        country?.code?.let {
            QuestionsActivity.startActivity(activity = this, countrySize = country.size, userName = userName)
        } ?: showError()
    }

    private fun showError() {
        Toast.makeText(this, getString(R.string.welcome_chooser_error), Toast.LENGTH_SHORT).show()
    }
}
