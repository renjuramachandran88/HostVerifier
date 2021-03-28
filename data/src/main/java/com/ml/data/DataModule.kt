package com.ml.data

import android.content.Context
import androidx.room.Room
import com.ml.data.local.VerifierDao
import com.ml.data.local.VerifierDatabase
import com.ml.data.mapper.VerifierDataMapper
import com.ml.data.repository.VerifierDataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    companion object {
        internal const val VERIFIER_DATABASE = "verifier_database"
    }

    @Provides
    @Singleton
    fun providesVerifierDatabase(context: Context): VerifierDatabase
    {
        return Room.databaseBuilder(
            context, VerifierDatabase::class.java, VERIFIER_DATABASE
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    @Provides
    fun providesVerifierDaoService(verifierDatabase: VerifierDatabase) =
        verifierDatabase.verifierDataDaoService()

    @Provides
    fun providesVerifierDataRepository(verifierDao: VerifierDao, mapper: VerifierDataMapper) =
       VerifierDataRepository(verifierDao, mapper)

}