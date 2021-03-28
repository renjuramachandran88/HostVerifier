package com.ml.hostverifier.home.list

import com.ml.hostverifier.home.HomeRecyclerViewModel
import com.ml.hostverifier.home.HomeRecyclerViewType.Companion.CLEAR_DATA_SEGMENT

class HomeClearButtonViewModel : HomeRecyclerViewModel {
    override fun getType(): Int {
        return CLEAR_DATA_SEGMENT
    }
}