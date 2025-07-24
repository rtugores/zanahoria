package huitca1212.cuantotemide.main.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import huitca1212.cuantotemide.BaseActivity
import huitca1212.cuantotemide.questions.presentation.QuestionsActivity
import huitca1212.cuantotemide.R
import huitca1212.cuantotemide.databinding.ActivityMainBinding
import huitca1212.cuantotemide.fromHtml
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var countriesAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.bind(findViewById(R.id.mainMainContainer))
        setSupportActionBar(binding.mainAppTopBarLayout.appTopBar)

        binding.mainWelcomeTextView.text = getString(R.string.welcome_text).fromHtml()
        binding.mainStartButton.setOnClickListener { onStartButtonClicked() }

        setupCountriesSpinner()
        observeViewModel()

        viewModel.loadCountries()
    }


    private fun setupCountriesSpinner() {
        countriesAdapter = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_spinner_dropdown_item,
            mutableListOf<String>(),
        )
        binding.mainCountrySpinner.adapter = countriesAdapter
        binding.mainCountrySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                viewModel.onCountrySelected(position)
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) = Unit
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    val countryNames = uiState.countries.map { it.name }
                    countriesAdapter.clear()
                    countriesAdapter.addAll(countryNames)
                    countriesAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun onStartButtonClicked() {
        val userName = binding.mainNameEditText.text.toString()
        val country = viewModel.uiState.value.selectedCountry

        country?.code?.let {
            QuestionsActivity.Companion.startActivity(
                activity = this,
                countrySize = country.size,
                userName = userName,
            )
        } ?: showError()
    }


    private fun showError() {
        Toast.makeText(this, getString(R.string.welcome_chooser_error), Toast.LENGTH_SHORT).show()
    }
}
