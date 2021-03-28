package com.ml.data

import android.content.Context
import com.ml.data.local.VerifierDao
import com.ml.data.local.VerifierDatabase
import com.ml.data.repository.VerifierDataRepository
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface DataComponent {
    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): DataComponent
    }

    fun veriferDataBase(): VerifierDatabase

    fun veriferDao(): VerifierDao
}
