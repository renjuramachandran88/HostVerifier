package com.ml.data.mapper

import com.ml.data.entity.VerifierEntity
import com.ml.domain.model.VerifierModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VerifierDataMapperTest {
    @InjectMocks
    private lateinit var subject: VerifierDataMapper

    @Test
    fun mapFrom_givenVerifierDataEntityList_returnVerifierDataModelList() {
        val actual = subject.mapFrom(buildVerifier())

        assertThat(actual.size).isEqualTo(4)

        assertThat(actual[0]).isEqualTo("test1")
        assertThat(actual[1]).isEqualTo("test2")
        assertThat(actual[2]).isEqualTo("test3")
        assertThat(actual[3]).isEqualTo("test4")

    }

    @Test
    fun mapVerifierModel_givenVerifierDataModel_returnsVerifierDataEntity() {
        val model = VerifierModel("test_data")
        val actual = subject.mapVerifierModel(model)
        assertThat(actual.verifierData).isEqualTo("test_data")

    }

    private fun buildVerifier(): List<VerifierEntity> {
        return listOf(
            VerifierEntity("test1"),
            VerifierEntity("test2"),
            VerifierEntity("test3"),
            VerifierEntity("test4")
        )
    }
}