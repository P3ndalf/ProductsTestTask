package com.productstesttask.ui.features.detail

import androidx.lifecycle.viewModelScope
import com.productstesttask.data.remote.service.base.onSuccess
import com.productstesttask.domain.base.architecture.BaseViewModel
import com.productstesttask.domain.use_cases.DeleteProductFromCardUseCase
import com.productstesttask.domain.use_cases.GetCardUseCase
import com.productstesttask.domain.use_cases.GetProductUseCase
import com.productstesttask.domain.use_cases.InsertProductInCardUseCase
import com.productstesttask.ui.features.detail.Action.ChangeCard
import com.productstesttask.ui.features.detail.Action.GetProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val getCardUseCase: GetCardUseCase,
    private val getProductUseCase: GetProductUseCase,
    private val insertProductInCardUseCase: InsertProductInCardUseCase,
    private val deleteProductFromCardUseCase: DeleteProductFromCardUseCase
) : BaseViewModel<Action, State>(State()) {

    override fun observeAction(action: Action) {
        when (action) {
            is GetProduct -> getProduct(action.productTitle)
            is ChangeCard -> changeCard(action.isAddedToCard)
        }
    }

    private fun getProduct(title: String) {
        viewModelScope.launch {
            getProductUseCase.invoke(title)
                .onSuccess { response ->
                    setState { copy(product = response) }
                    getCardUseCase.invoke(Unit)
                        .onSuccess {
                            it.collect { card ->
                                setState { copy(isAddedToCard = card.contains(uiState.value.product)) }
                            }
                        }
                }
        }
    }

    private fun changeCard(isAddedToCard: Boolean) {
        viewModelScope.launch {
            if (isAddedToCard) insertProductInCardUseCase.invoke(uiState.value.product)
            else deleteProductFromCardUseCase.invoke(uiState.value.product)
            setState { copy(isAddedToCard = isAddedToCard) }
        }
    }
}
