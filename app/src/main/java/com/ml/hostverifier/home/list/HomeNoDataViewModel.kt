package com.ml.hostverifier.home.list

import com.ml.hostverifier.home.HomeRecyclerViewModel
import com.ml.hostverifier.home.HomeRecyclerViewType.Companion.NO_DATA_SEGMENT

class HomeNoDataViewModel : HomeRecyclerViewModel {
    override fun getType(): Int {
        return NO_DATA_SEGMENT
    }
}