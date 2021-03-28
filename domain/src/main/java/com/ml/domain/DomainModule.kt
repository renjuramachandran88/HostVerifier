package com.ml.domain

import com.ml.domain.repository.VerifierRepository
import com.ml.domain.usecase.VerifierUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideVerifierDataUseCase(repository: VerifierRepository): VerifierUseCase =
        VerifierUseCase(repository)
}