package com.ml.hostverifier.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ml.domain.model.VerifierModel
import com.ml.domain.usecase.VerifierUseCase
import com.ml.hostverifier.HostApplication
import com.ml.hostverifier.R
import com.ml.hostverifier.databinding.ActivityHomeBinding
import com.ml.hostverifier.home.HomeActivity.IntentExtra.VERIFIER_DATA_LIST
import com.ml.hostverifier.home.list.HomeAddButtonViewHolder
import com.ml.hostverifier.home.list.HomeClearButtonViewHolder
import com.ml.hostverifier.splash.VerifierAppDataMapper
import com.ml.hostverifier.splash.VerifierData
import com.ml.verifier.documentreader.CameraPreviewComponentManager
import com.ml.verifier.documentreader.DocumentResult
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeActivity : AppCompatActivity(),
    HomeAddButtonViewHolder.OnAddDataClickedCallback,
    HomeClearButtonViewHolder.OnClearDataClickedCallback {

    @Inject
     lateinit var useCase: VerifierUseCase

    @Inject
     lateinit var factory: HomeRecyclerViewModelFactory

    @Inject
     lateinit var mapper: VerifierAppDataMapper

    @Inject
     lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var adapter: HomeAdapter
    private lateinit var homeViewBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as HostApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        homeViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setupAdapter()
        generateViewModels(getVerifiedDataList())
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DOCUMENT_READER_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.let {
                val documentResult: DocumentResult = data.getParcelableExtra("document_result")!!
                if (documentResult is DocumentResult.Success) {
                    val s = documentResult.capturedString
                    insertVerifierData(s)
                } else {
                    showDisplay("Something went wrong Please try again")
                }
            }
        }
    }

    override fun onAddDataButtonClicked() {
        CameraPreviewComponentManager.startCameraPreviewActivity(this, DOCUMENT_READER_REQUEST_CODE)
    }

    override fun onClearDataButtonClicked() {
        clearData()
    }

    private fun getVerifiedDataList(): List<VerifierData> {
        return intent.getParcelableArrayListExtra(VERIFIER_DATA_LIST)!!
    }

    private fun setupAdapter() {
        homeViewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        homeViewBinding.recyclerView.adapter = adapter
        adapter.setOnAddDataClickedCallback(this)
        adapter.setOnClearDataClickedCallback(this)
    }

    private fun generateViewModels(verifiedDataList: List<VerifierData>) {
        val list = factory.generateViewModels(verifiedDataList)
        adapter.setViewModels(list)
    }

    private fun insertVerifierData(detectedString: String) {
        val model = VerifierModel(detectedString)
        useCase.insertUseCase(model)
        updateHomeViews()
    }

    private fun updateHomeViews() {
        val disposables = useCase.getVerifierDataUseCase()
            .subscribe {
                val verifiedList = mapper.mapTo(it)
                val viewModels = factory.generateViewModels(verifiedList)
                adapter.setViewModels(viewModels)
            }
        compositeDisposable.add(disposables)
    }

    private fun clearData() {
        useCase.clearDataUseCase()
        updateHomeViews()
    }

    private fun showDisplay(toastMessage: String){
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
    }

    object IntentExtra {
        const val VERIFIER_DATA_LIST = "verifier_data_list"
    }


    companion object {
        const val DOCUMENT_READER_REQUEST_CODE = 1000

        fun startInstance(
            context: Context,
            verifierDataList: List<VerifierData>?
        ) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putParcelableArrayListExtra(VERIFIER_DATA_LIST, ArrayList(verifierDataList))
            context.startActivity(intent)
        }
    }
}