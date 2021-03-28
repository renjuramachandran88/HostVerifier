package com.ml.domain.usecase

import com.ml.domain.model.VerifierModel
import com.ml.domain.repository.VerifierRepository
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VerifierUseCaseTest {
    @InjectMocks
    private lateinit var subject: VerifierUseCase

    @Mock
    private lateinit var repository: VerifierRepository

    @Mock
    private lateinit var verifierModel: VerifierModel

    @Test
    fun insertUseCase_givenVerifierData_insertsDataRepo() {
        subject.insertUseCase(verifierModel)
        verify(repository).insertVerifierData(verifierModel)
    }

    @Test
    fun getVerifierDataUseCase_returnsVerifierData() {
        `when`(repository.getVerifierData()).thenReturn(Observable.just(listOf(verifierModel)))
        val actual = subject.getVerifierDataUseCase().test()
        actual.assertValue(listOf(verifierModel))
    }

    @Test
    fun clear() {
        subject.clearDataUseCase()
        verify(repository).clear()
    }
}

