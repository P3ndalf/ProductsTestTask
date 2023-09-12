package com.productstesttask.ui.features.main

import com.productstesttask.domain.base.architecture.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<Action, State>(State()) {

    override fun observeAction(action: Action) {
        TODO("Not yet implemented")
    }
}
