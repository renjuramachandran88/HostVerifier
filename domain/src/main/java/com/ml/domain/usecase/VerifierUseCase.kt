package com.ml.domain.usecase

import com.ml.domain.model.VerifierModel
import com.ml.domain.repository.VerifierRepository
import io.reactivex.Observable
import javax.inject.Inject

class VerifierUseCase @Inject constructor(
    private val repository: VerifierRepository
) {
    fun insertUseCase(verifierModel: VerifierModel) =
        repository.insertVerifierData(verifierModel)


    fun getVerifierDataUseCase(): Observable<List<VerifierModel>>
        = repository.getVerifierData()


    fun clearDataUseCase() = repository.clear()

}