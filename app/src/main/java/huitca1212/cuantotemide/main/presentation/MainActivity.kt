package huitca1212.cuantotemide.main.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import huitca1212.cuantotemide.R
import huitca1212.cuantotemide.ui.theme.AppTheme
import huitca1212.cuantotemide.questions.presentation.QuestionsActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                MainScreen(
                    uiState = uiState,
                    onUserNameChanged = viewModel::onUserNameChanged,
                    onCountrySelected = viewModel::onCountrySelected,
                    onStartClicked = ::onStartButtonClicked,
                )
            }
        }

        viewModel.loadCountries()
    }

    private fun onStartButtonClicked() {
        val uiState = viewModel.uiState.value
        if (uiState.selectedCountry?.code != null) {
            QuestionsActivity.startActivity(
                activity = this,
                countrySize = uiState.selectedCountry.size,
                userName = uiState.userName
            )
        } else {
            Toast.makeText(this@MainActivity, R.string.welcome_chooser_error, Toast.LENGTH_SHORT).show()
        }
    }
}
