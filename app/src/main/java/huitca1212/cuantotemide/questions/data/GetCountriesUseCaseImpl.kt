package huitca1212.cuantotemide.questions.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import huitca1212.cuantotemide.R
import huitca1212.cuantotemide.questions.data.model.Country
import java.util.Locale
import javax.inject.Inject

internal class GetCountriesUseCaseImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : GetCountriesUseCase {

    override fun getCountriesWithDefault(): List<Country> {
        val default = Country(null, context.getString(R.string.welcome_chooser_text), size = 0f)
        return listOf(default) + getCountriesByName()
    }

    private fun getCountriesByName(): List<Country> {
        return countryCodesRes.map { countryCodeResAndSize ->
            val countryCode = context.getString(countryCodeResAndSize.first)
            Country(
                code = countryCode,
                name = Locale("", countryCode).displayCountry,
                size = countryCodeResAndSize.second
            )
        }
    }

    private val countryCodesRes = listOf(
        Pair(R.string.welcome_country_andorra, 13.85f),
        Pair(R.string.welcome_country_angola, 15.73f),
        Pair(R.string.welcome_country_argentina, 14.88f),
        Pair(R.string.welcome_country_belice, 14.88f),
        Pair(R.string.welcome_country_bolivia, 16.51f),
        Pair(R.string.welcome_country_brasil, 15.22f),
        Pair(R.string.welcome_country_canada, 15.71f),
        Pair(R.string.welcome_country_chile, 14.59f),
        Pair(R.string.welcome_country_cabo_verde, 14.05f),
        Pair(R.string.welcome_country_colombia, 15.26f),
        Pair(R.string.welcome_country_costa_rica, 15.01f),
        Pair(R.string.welcome_country_cuba, 15.87f),
        Pair(R.string.welcome_country_ecuador, 17.61f),
        Pair(R.string.welcome_country_el_salvador, 14.88f),
        Pair(R.string.welcome_country_espanha, 13.85f),
        Pair(R.string.welcome_country_estados_unidos, 13.58f),
        Pair(R.string.welcome_country_francia, 15.74f),
        Pair(R.string.welcome_country_guatemala, 15.01f),
        Pair(R.string.welcome_country_guinea_ecuatorial, 16.47f),
        Pair(R.string.welcome_country_haiti, 16.01f),
        Pair(R.string.welcome_country_honduras, 15.00f),
        Pair(R.string.welcome_country_marruecos, 13.98f),
        Pair(R.string.welcome_country_mexico, 14.92f),
        Pair(R.string.welcome_country_mozambique, 15.73f),
        Pair(R.string.welcome_country_nicaragua, 14.88f),
        Pair(R.string.welcome_country_otro, 13.58f),
        Pair(R.string.welcome_country_panama, 15.49f),
        Pair(R.string.welcome_country_portugal, 13.78f),
        Pair(R.string.welcome_country_paraguay, 15.53f),
        Pair(R.string.welcome_country_peru, 16.36f),
        Pair(R.string.welcome_country_puerto_rico, 16.49f),
        Pair(R.string.welcome_country_republica_dominicana, 16.39f),
        Pair(R.string.welcome_country_trinidad_y_tobago, 15.49f),
        Pair(R.string.welcome_country_uruguay, 15.64f),
        Pair(R.string.welcome_country_venezuela, 13.33f),
    )
}
