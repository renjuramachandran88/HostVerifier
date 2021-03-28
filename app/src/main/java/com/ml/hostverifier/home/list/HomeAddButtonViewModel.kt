package com.ml.hostverifier.home.list

import com.ml.hostverifier.home.HomeRecyclerViewModel
import com.ml.hostverifier.home.HomeRecyclerViewType.Companion.ADD_DATA_SEGMENT

class HomeAddButtonViewModel: HomeRecyclerViewModel {
    override fun getType(): Int {
        return ADD_DATA_SEGMENT
    }
}