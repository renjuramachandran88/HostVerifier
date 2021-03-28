package com.ml.hostverifier.splash

import com.ml.domain.model.VerifierModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VerifierAppDataMapperTest {
    @InjectMocks
    private lateinit var subject: VerifierAppDataMapper

    @Test
    fun mapTo_givenVerifierModelList_returnVerifierDataList() {
        val actual = subject.mapTo(buildVerifierModelList())

        assertThat(actual.size).isEqualTo(3)

        assertThat(actual[0]).isEqualTo("data1")
        assertThat(actual[1]).isEqualTo("data2")
        assertThat(actual[2]).isEqualTo("data3")
    }

    private fun buildVerifierModelList(): List<VerifierModel> {
        return listOf(
            VerifierModel("data1"),
            VerifierModel("data2"),
            VerifierModel("data3")
        )

    }
}