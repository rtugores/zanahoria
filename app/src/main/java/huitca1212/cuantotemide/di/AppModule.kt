package huitca1212.cuantotemide.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import huitca1212.cuantotemide.questions.data.GetCountriesUseCase
import huitca1212.cuantotemide.questions.data.GetCountriesUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class AppModule {

    @Binds
    abstract fun provideGetCountriesUseCase(useCase: GetCountriesUseCaseImpl): GetCountriesUseCase
}
