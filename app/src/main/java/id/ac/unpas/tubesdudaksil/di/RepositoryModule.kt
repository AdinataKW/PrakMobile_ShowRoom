package id.ac.unpas.tubesdudaksil.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.tubesdudaksil.networks.MobilApi
import id.ac.unpas.tubesdudaksil.persistences.MobilDao
import id.ac.unpas.tubesdudaksil.repositories.MobilRepository
import id.ac.unpas.tubesdudaksil.networks.SepedaMotorApi
import id.ac.unpas.tubesdudaksil.persistences.SepedaMotorDao
import id.ac.unpas.tubesdudaksil.repositories.SepedaMotorRepository
import id.ac.unpas.tubesdudaksil.networks.PromoApi
import id.ac.unpas.tubesdudaksil.persistences.PromoDao
import id.ac.unpas.tubesdudaksil.repositories.PromoRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideMobilRepository(
        api: MobilApi,
        dao: MobilDao
    ): MobilRepository {
        return MobilRepository(api, dao)
    }
    @Provides
    @ViewModelScoped
    fun provideSepedaMotorRepository (
        api: SepedaMotorApi,
        dao: SepedaMotorDao
    ): SepedaMotorRepository {
        return SepedaMotorRepository(api, dao)
    }
    @Provides
    @ViewModelScoped
    fun providePromoRepository(
        api: PromoApi,
        dao: PromoDao
    ): PromoRepository {
        return PromoRepository(api, dao)
    }
}