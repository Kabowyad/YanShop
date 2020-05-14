package com.example.yanshop.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import moxy.MvpPresenter
import moxy.MvpView
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<TView : MvpView> : MvpPresenter<TView>()

abstract class CoroutinePresenter<TView : MvpView> : BasePresenter<TView>(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}