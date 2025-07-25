package huitca1212.cuantotemide.questions.data

import huitca1212.cuantotemide.questions.data.model.Country

internal interface GetCountriesUseCase {

    fun getCountriesWithDefault(): List<Country>
}

