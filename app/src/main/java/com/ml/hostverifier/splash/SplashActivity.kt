package com.ml.hostverifier.splash

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ml.domain.usecase.VerifierUseCase
import com.ml.hostverifier.HostApplication
import com.ml.hostverifier.R
import com.ml.hostverifier.databinding.ActivitySplashBinding
import com.ml.hostverifier.home.HomeActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var useCase: VerifierUseCase

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var mapper: VerifierAppDataMapper


    private lateinit var viewDataBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as HostApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        fetchData()
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    private fun showLoadingSpinner() {
        viewDataBinding.splashWelcomeProgress.visibility = View.VISIBLE
    }

    private fun hideLoadingSpinner() {
        viewDataBinding.splashWelcomeProgress.visibility = View.GONE
    }

    private fun fetchData() {
        val disposable = useCase.getVerifierDataUseCase()
            .doOnSubscribe{showLoadingSpinner()}
            .doOnTerminate{hideLoadingSpinner()}
            .subscribe({ list ->
            if (list.isNotEmpty()) {
                HomeActivity.startInstance(this, mapper.mapTo(list))
            } else {
                HomeActivity.startInstance(this, emptyList())
            }
        }, {
            HomeActivity.startInstance(this, emptyList())
        }

        )

        compositeDisposable.add(disposable)
    }
}