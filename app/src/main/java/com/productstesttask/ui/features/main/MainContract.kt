package com.productstesttask.ui.features.main

import com.productstesttask.domain.base.architecture.UiAction
import com.productstesttask.domain.base.architecture.UiState
import com.productstesttask.domain.models.Product

sealed interface Action : UiAction {
    data class UpdateCardVisibility(val isCardOpened: Boolean) : Action
}

data class State(
    val products: List<Product> = emptyList(),
    val card: List<Product> = emptyList(),
    val isCardOpened: Boolean = false
) : UiState
