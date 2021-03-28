package com.ml.hostverifier.home.list

import androidx.recyclerview.widget.RecyclerView
import com.ml.hostverifier.databinding.ItemHomeVerifiedDataViewBinding

class HomeVerifiedDataViewHolder(private val binding: ItemHomeVerifiedDataViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindView(viewModel: HomeVerifierDataViewModel) {
        binding.homeVerifiedDataText.text = viewModel.gatheredString
    }
}
