package com.ml.data.repository

import com.ml.data.entity.VerifierEntity
import com.ml.data.local.VerifierDao
import com.ml.data.mapper.VerifierDataMapper
import com.ml.domain.model.VerifierModel
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VerifierDataRepositoryTest {
    @InjectMocks
    private lateinit var subject: VerifierDataRepository

    @Mock
    private lateinit var verifierDao: VerifierDao

    @Mock
    private lateinit var mapper: VerifierDataMapper

    @Mock
    private lateinit var verifierEntity: VerifierEntity

    @Mock
    private lateinit var verifierModel: VerifierModel

    @Test
    fun getVerifierData_givenSuccess_returnVerifierDataList() {
        `when`(verifierDao.getVerifierData()).thenReturn(listOf(verifierEntity))
        `when`(mapper.mapFrom(listOf(verifierEntity))).thenReturn(listOf(verifierModel))

        val actual = subject.getVerifierData().test()

        verify(verifierDao).getVerifierData()
        actual.assertValue(listOf(verifierModel))
    }

    @Test
    fun getVerifierData_givenFailure_returnEmptyList() {
        `when`(verifierDao.getVerifierData()).thenReturn(emptyList())

        val actual = subject.getVerifierData().test()

        actual.assertValue(emptyList())
    }

    @Test
    fun insertVerifierData_givenVerifierData_insertsData() {
        `when`(mapper.mapVerifierModel(verifierModel)).thenReturn(verifierEntity)

        subject.insertVerifierData(verifierModel)
        verify(verifierDao).insertVerifierData(verifierEntity)
    }

    @Test
    fun clear_clearsData() {
        subject.clear()
        verify(verifierDao).clear()
    }


}