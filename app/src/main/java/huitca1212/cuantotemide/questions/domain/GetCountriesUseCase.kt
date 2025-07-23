package huitca1212.cuantotemide.questions.domain

import huitca1212.cuantotemide.questions.domain.model.Country

internal interface GetCountriesUseCase {
    fun getCountriesWithDefault(): List<Country>
}

