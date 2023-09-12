package com.productstesttask.ui.features.detail

import com.productstesttask.domain.base.architecture.UiAction
import com.productstesttask.domain.base.architecture.UiState
import com.productstesttask.domain.models.Product
import com.productstesttask.domain.models.Product.Companion.DEFAULT

sealed interface Action : UiAction {
    data class GetProduct(val productTitle: String) : Action
    data class ChangeCard(val isAddedToCard: Boolean) : Action
}

data class State(
    val product: Product = DEFAULT,
    val isAddedToCard: Boolean = false
) : UiState
