package huitca1212.cuantotemide

import android.content.Context
import java.util.Locale

internal class GetCountriesUseCase(private val context: Context) {

    fun getCountriesWithDefault(): List<Country> =
        listOf(Country(null, context.getString(R.string.welcome_chooser_text))) + getCountriesByName()

    private fun getCountriesByName(): List<Country> {
        return getCountryCodesRes()
            .map { countryCodeRes ->
                val countryCode = context.getString(countryCodeRes)
                Country(code = countryCode, name = Locale("", countryCode).displayCountry)
            }
    }

    private fun getCountryCodesRes(): List<Int> {
        return listOf(
            R.string.welcome_country_andorra,
            R.string.welcome_country_angola,
            R.string.welcome_country_argentina,
            R.string.welcome_country_belice,
            R.string.welcome_country_bolivia,
            R.string.welcome_country_brasil,
            R.string.welcome_country_canada,
            R.string.welcome_country_chile,
            R.string.welcome_country_cabo_verde,
            R.string.welcome_country_colombia,
            R.string.welcome_country_costa_rica,
            R.string.welcome_country_cuba,
            R.string.welcome_country_ecuador,
            R.string.welcome_country_el_salvador,
            R.string.welcome_country_espanha,
            R.string.welcome_country_estados_unidos,
            R.string.welcome_country_francia,
            R.string.welcome_country_guatemala,
            R.string.welcome_country_guinea_ecuatorial,
            R.string.welcome_country_haiti,
            R.string.welcome_country_honduras,
            R.string.welcome_country_marruecos,
            R.string.welcome_country_mexico,
            R.string.welcome_country_mozambique,
            R.string.welcome_country_nicaragua,
            R.string.welcome_country_otro,
            R.string.welcome_country_panama,
            R.string.welcome_country_portugal,
            R.string.welcome_country_paraguay,
            R.string.welcome_country_peru,
            R.string.welcome_country_puerto_rico,
            R.string.welcome_country_republica_dominicana,
            R.string.welcome_country_trinidad_y_tobago,
            R.string.welcome_country_uruguay,
            R.string.welcome_country_venezuela
        )
    }
}
