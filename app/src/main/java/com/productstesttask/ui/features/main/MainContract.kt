package com.productstesttask.ui.features.main

import com.productstesttask.data.models.Product
import com.productstesttask.domain.base.architecture.UiAction
import com.productstesttask.domain.base.architecture.UiState

sealed interface Action : UiAction {

}

data class State(
    val products: List<Product> = emptyList()
) : UiState
