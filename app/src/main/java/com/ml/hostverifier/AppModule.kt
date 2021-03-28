package com.ml.hostverifier

import android.content.Context
import com.ml.data.DataLibrary
import com.ml.data.local.VerifierDao
import com.ml.data.local.VerifierDatabase
import com.ml.data.repository.VerifierDataRepository
import com.ml.domain.DomainLibrary
import com.ml.domain.repository.VerifierRepository
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@Module
class AppModule {
    @Provides
    fun providesCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun providesDomainLibrary(): DomainLibrary = DomainLibrary()

    @Provides
    fun providesDataLibrary(context: Context): DataLibrary = DataLibrary(context)

    @Provides
    fun providesVerifierDatabase(
        dataLibrary: DataLibrary
    ): VerifierDatabase {

        return dataLibrary.veriferDataBase()
    }

    @Provides
    fun providesVerifierDao(
        dataLibrary: DataLibrary
    ): VerifierDao {

        return dataLibrary.veriferDao()
    }

    @Provides
    fun providesRepository(verifierDataRepository: VerifierDataRepository)
            : VerifierRepository = verifierDataRepository

}