package com.ml.hostverifier.home.list

import androidx.recyclerview.widget.RecyclerView
import com.ml.hostverifier.databinding.ItemHomeAddButtonViewBinding

class HomeAddButtonViewHolder(private val binding: ItemHomeAddButtonViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindView(callback: OnAddDataClickedCallback) {
        binding.homeAddButton.setOnClickListener {
            callback.onAddDataButtonClicked()
        }
    }

    interface OnAddDataClickedCallback {
        fun onAddDataButtonClicked()
    }
}