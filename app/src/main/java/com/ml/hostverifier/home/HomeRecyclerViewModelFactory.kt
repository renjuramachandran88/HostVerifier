package com.ml.hostverifier.home

import com.ml.hostverifier.home.list.HomeAddButtonViewModel
import com.ml.hostverifier.home.list.HomeClearButtonViewModel
import com.ml.hostverifier.home.list.HomeNoDataViewModel
import com.ml.hostverifier.home.list.HomeVerifierDataViewModel
import com.ml.hostverifier.splash.VerifierData
import javax.inject.Inject

class HomeRecyclerViewModelFactory @Inject constructor() {

    fun generateViewModels(verifierData: List<VerifierData>): List<HomeRecyclerViewModel> {
        val viewModels = mutableListOf<HomeRecyclerViewModel>()
        if (verifierData.isNotEmpty()) {
            verifierData.map {
                viewModels.add(HomeVerifierDataViewModel(it.verifierData))
            }
        } else {
            viewModels.add(HomeNoDataViewModel())
        }
        viewModels.add(HomeAddButtonViewModel())
        viewModels.add(HomeClearButtonViewModel())
        return viewModels
    }
}