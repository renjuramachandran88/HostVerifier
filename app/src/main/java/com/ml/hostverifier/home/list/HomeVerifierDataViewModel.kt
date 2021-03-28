package com.ml.hostverifier.home.list

import com.ml.hostverifier.home.HomeRecyclerViewModel
import com.ml.hostverifier.home.HomeRecyclerViewType.Companion.VERIFIER_DATA_SEGMENT

class HomeVerifierDataViewModel(val gatheredString: String) : HomeRecyclerViewModel {
    override fun getType(): Int {
        return VERIFIER_DATA_SEGMENT
    }
}