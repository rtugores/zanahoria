package huitca1212.cuantotemide.main.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import huitca1212.cuantotemide.questions.domain.model.Country
import huitca1212.cuantotemide.questions.domain.GetCountriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal data class MainUiState(
    val countries: List<Country> = emptyList(),
    val selectedCountry: Country? = null,
)

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())

    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private var internalCountriesList: List<Country> = emptyList()

    fun loadCountries() {
        internalCountriesList = getCountriesUseCase.getCountriesWithDefault()
        _uiState.update { currentState ->
            currentState.copy(countries = internalCountriesList)
        }
    }

    fun onCountrySelected(position: Int) {
        val selected = if (position in internalCountriesList.indices) {
            internalCountriesList[position]
        } else {
            null
        }
        _uiState.update { currentState ->
            currentState.copy(selectedCountry = selected)
        }
    }
}
