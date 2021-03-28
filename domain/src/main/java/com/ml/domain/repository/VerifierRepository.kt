package com.ml.domain.repository

import com.ml.domain.model.VerifierModel
import io.reactivex.Observable

interface VerifierRepository {
    fun getVerifierData(): Observable<List<VerifierModel>>
    fun insertVerifierData(verifierModel: VerifierModel)
    fun clear()
}