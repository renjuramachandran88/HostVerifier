package com.ml.hostverifier.home.list

import androidx.recyclerview.widget.RecyclerView
import com.ml.hostverifier.databinding.ItemHomeClearButtonViewBinding

class HomeClearButtonViewHolder(private val binding: ItemHomeClearButtonViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindView(callback: OnClearDataClickedCallback) {
        binding.homeClearButton.setOnClickListener {
            callback.onClearDataButtonClicked()
        }
    }

    interface OnClearDataClickedCallback {
        fun onClearDataButtonClicked()
    }
}