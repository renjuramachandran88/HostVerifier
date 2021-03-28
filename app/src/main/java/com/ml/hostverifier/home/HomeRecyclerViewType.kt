package com.ml.hostverifier.home

import androidx.annotation.IntDef
import com.ml.hostverifier.home.HomeRecyclerViewType.Companion.ADD_DATA_SEGMENT
import com.ml.hostverifier.home.HomeRecyclerViewType.Companion.CLEAR_DATA_SEGMENT
import com.ml.hostverifier.home.HomeRecyclerViewType.Companion.NO_DATA_SEGMENT
import com.ml.hostverifier.home.HomeRecyclerViewType.Companion.VERIFIER_DATA_SEGMENT

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    VERIFIER_DATA_SEGMENT,
    NO_DATA_SEGMENT,
    ADD_DATA_SEGMENT,
    CLEAR_DATA_SEGMENT
)
annotation class HomeRecyclerViewType {
    companion object {
        const val VERIFIER_DATA_SEGMENT = 0
        const val NO_DATA_SEGMENT = 1
        const val ADD_DATA_SEGMENT = 2
        const val CLEAR_DATA_SEGMENT = 3
    }
}