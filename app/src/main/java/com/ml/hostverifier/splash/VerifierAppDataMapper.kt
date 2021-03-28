package com.ml.hostverifier.splash

import com.ml.domain.model.VerifierModel
import javax.inject.Inject

class VerifierAppDataMapper @Inject constructor() {

    fun mapTo(list: List<VerifierModel>): List<VerifierData> {
        return list.map {
            VerifierData(it.verifierData)
        }
    }
}