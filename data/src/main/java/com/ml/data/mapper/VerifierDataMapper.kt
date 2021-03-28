package com.ml.data.mapper

import com.ml.data.entity.VerifierEntity
import com.ml.domain.model.VerifierModel
import javax.inject.Inject

class VerifierDataMapper @Inject constructor() {
    fun mapFrom(verifierDataList: List<VerifierEntity>): List<VerifierModel> {
        return verifierDataList.map {
            VerifierModel(it.verifierData)
        }
    }

    fun mapVerifierModel(verifierModel: VerifierModel): VerifierEntity {
        return VerifierEntity(verifierModel.verifierData)
    }
}