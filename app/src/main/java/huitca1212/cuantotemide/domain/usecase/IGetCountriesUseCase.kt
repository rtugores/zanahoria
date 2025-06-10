package huitca1212.cuantotemide.domain.usecase

import huitca1212.cuantotemide.domain.model.Country

internal interface IGetCountriesUseCase {
    fun getCountriesWithDefault(): List<Country>
}

