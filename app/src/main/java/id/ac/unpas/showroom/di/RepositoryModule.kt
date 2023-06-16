package id.ac.unpas.showroom.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.showroom.networks.SR_MobilApi
import id.ac.unpas.showroom.persistences.SR_MobilDao
import id.ac.unpas.showroom.repositories.SR_MobilRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideSetoranSampahRepository(
        api: SR_MobilApi,
        dao: SR_MobilDao
    ): SR_MobilRepository {
        return SR_MobilRepository(api, dao)
    }
}