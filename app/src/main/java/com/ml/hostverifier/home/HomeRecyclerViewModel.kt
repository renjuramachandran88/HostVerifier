package com.ml.hostverifier.home

interface HomeRecyclerViewModel {
    @HomeRecyclerViewType
    fun getType(): Int
}