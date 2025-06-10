package huitca1212.cuantotemide

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import huitca1212.cuantotemide.domain.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCaseImpl
) : ViewModel() {
    private var countries: List<Country> = emptyList()
    var selectedCountry: Country? = null
        private set

    suspend fun loadCountries(): List<Country> = withContext(Dispatchers.IO) {
        countries = getCountriesUseCase.getCountriesWithDefault()
        countries
    }

    fun selectCountry(position: Int) {
        selectedCountry = if (position in countries.indices) {
            countries[position]
        } else {
            null
        }
    }

    fun getCountries(): List<Country> = countries
}
