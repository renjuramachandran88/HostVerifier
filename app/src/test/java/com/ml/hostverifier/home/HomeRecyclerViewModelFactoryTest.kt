package com.ml.hostverifier.home

import com.ml.hostverifier.home.list.HomeAddButtonViewModel
import com.ml.hostverifier.home.list.HomeClearButtonViewModel
import com.ml.hostverifier.home.list.HomeNoDataViewModel
import com.ml.hostverifier.home.list.HomeVerifierDataViewModel
import com.ml.hostverifier.splash.VerifierData
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeRecyclerViewModelFactoryTest {
    @InjectMocks
    private lateinit var subject: HomeRecyclerViewModelFactory

    @Test
    fun generateViewModels_givenVerifierDataList_addViewModels() {
        val actual = subject.generateViewModels(createVerifierDataList())

        assertThat(actual.size).isEqualTo(5)
        assertThat(actual[0]).isInstanceOf(HomeVerifierDataViewModel::class.java)
        assertThat(actual[1]).isInstanceOf(HomeVerifierDataViewModel::class.java)
        assertThat(actual[2]).isInstanceOf(HomeVerifierDataViewModel::class.java)
        assertThat(actual[3]).isInstanceOf(HomeAddButtonViewModel::class.java)
        assertThat(actual[4]).isInstanceOf(HomeClearButtonViewModel::class.java)
    }

    @Test
    fun generateViewModels_givenEmptyList_addViewModels() {
        val actual = subject.generateViewModels(emptyList())

        assertThat(actual.size).isEqualTo(3)
        assertThat(actual[0]).isInstanceOf(HomeNoDataViewModel::class.java)
        assertThat(actual[1]).isInstanceOf(HomeAddButtonViewModel::class.java)
        assertThat(actual[2]).isInstanceOf(HomeClearButtonViewModel::class.java)
    }

    private fun createVerifierDataList(): List<VerifierData> {
        return listOf(
            VerifierData("test1"),
            VerifierData("test2"),
            VerifierData("test3")
        )

    }

}