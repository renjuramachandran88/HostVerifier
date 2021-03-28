package com.ml.data.repository

import com.ml.data.local.VerifierDao
import com.ml.data.mapper.VerifierDataMapper
import com.ml.domain.model.VerifierModel
import com.ml.domain.repository.VerifierRepository
import io.reactivex.Observable
import javax.inject.Inject

class VerifierDataRepository @Inject constructor(
    private val verifierDao: VerifierDao,
    private val mapper: VerifierDataMapper
) : VerifierRepository {
    override fun getVerifierData(): Observable<List<VerifierModel>> {
        val list = verifierDao.getVerifierData()
        return Observable.just(mapper.mapFrom(list))

    }

    override fun insertVerifierData(verifierModel: VerifierModel) {
        verifierDao.insertVerifierData(mapper.mapVerifierModel(verifierModel))
    }

    override fun clear() {
        verifierDao.clear()
    }
}