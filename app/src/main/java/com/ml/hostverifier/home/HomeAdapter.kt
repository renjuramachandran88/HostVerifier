package com.ml.hostverifier.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ml.hostverifier.databinding.ItemHomeAddButtonViewBinding
import com.ml.hostverifier.databinding.ItemHomeClearButtonViewBinding
import com.ml.hostverifier.databinding.ItemHomeNoDataViewBinding
import com.ml.hostverifier.databinding.ItemHomeVerifiedDataViewBinding
import com.ml.hostverifier.home.list.*
import com.ml.hostverifier.home.list.HomeAddButtonViewHolder.OnAddDataClickedCallback
import com.ml.hostverifier.home.list.HomeClearButtonViewHolder.OnClearDataClickedCallback
import javax.inject.Inject

class HomeAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var viewModels: List<HomeRecyclerViewModel> = mutableListOf()
    private lateinit var onAddDataClickedCallback: OnAddDataClickedCallback
    private lateinit var onClearDataClickedCallback: OnClearDataClickedCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HomeRecyclerViewType.VERIFIER_DATA_SEGMENT -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHomeVerifiedDataViewBinding.inflate(layoutInflater, parent, false)
                HomeVerifiedDataViewHolder(binding)
            }

            HomeRecyclerViewType.NO_DATA_SEGMENT -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHomeNoDataViewBinding.inflate(layoutInflater, parent, false)
                HomeNoDataViewHolder(binding)
            }
            HomeRecyclerViewType.ADD_DATA_SEGMENT -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHomeAddButtonViewBinding.inflate(layoutInflater, parent, false)
                HomeAddButtonViewHolder(binding)
            }
            HomeRecyclerViewType.CLEAR_DATA_SEGMENT -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHomeClearButtonViewBinding.inflate(layoutInflater, parent, false)
                HomeClearButtonViewHolder(binding)
            }
            else -> throw IllegalStateException(
                "Unknown type $viewType for HomeAdapter"
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewModel = viewModels[position]
        when (viewModel.getType()) {
            HomeRecyclerViewType.VERIFIER_DATA_SEGMENT -> {
                (holder as HomeVerifiedDataViewHolder).bindView(viewModel as HomeVerifierDataViewModel)
            }
            HomeRecyclerViewType.NO_DATA_SEGMENT -> {
                (holder as HomeNoDataViewHolder).bindView()
            }
            HomeRecyclerViewType.ADD_DATA_SEGMENT -> {
                (holder as HomeAddButtonViewHolder).bindView(onAddDataClickedCallback)
            }
            HomeRecyclerViewType.CLEAR_DATA_SEGMENT -> {
                (holder as HomeClearButtonViewHolder).bindView(onClearDataClickedCallback)
            }
            else -> throw java.lang.IllegalStateException(
                "Unknown Type" + viewModel.getType().toString() + "for home adapter"
            )
        }
    }

    override fun getItemCount(): Int {
        return viewModels.size
    }

    override fun getItemViewType(position: Int): Int {
        return viewModels[position].getType()
    }

    fun setOnAddDataClickedCallback(onAddDataClickedCallback: OnAddDataClickedCallback) {
        this.onAddDataClickedCallback = onAddDataClickedCallback
    }

    fun setOnClearDataClickedCallback(onClearDataClickedCallback: OnClearDataClickedCallback) {
        this.onClearDataClickedCallback = onClearDataClickedCallback
    }

    fun setViewModels(viewModel: List<HomeRecyclerViewModel>){
        this.viewModels = viewModel
        notifyDataSetChanged()
    }

}