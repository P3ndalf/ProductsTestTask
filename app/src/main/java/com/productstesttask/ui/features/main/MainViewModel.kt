package com.productstesttask.ui.features.main

import androidx.lifecycle.viewModelScope
import com.productstesttask.data.remote.service.base.onFailure
import com.productstesttask.data.remote.service.base.onSuccess
import com.productstesttask.domain.base.architecture.BaseViewModel
import com.productstesttask.domain.use_cases.GetCardUseCase
import com.productstesttask.domain.use_cases.GetProductsUseCase
import com.productstesttask.ui.features.main.Action.UpdateCardVisibility
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCardUseCase: GetCardUseCase
) : BaseViewModel<Action, State>(State()) {

    init {
        getProducts()
        getCard()
    }

    private fun getProducts() {
        viewModelScope.launch {
            getProductsUseCase.invoke(Unit)
                .onSuccess { response ->
                    setState { copy(products = response) }
                }
                .onFailure { error ->
                    // Here we can observe any errors we want
                }
        }
    }

    private fun getCard() {
        viewModelScope.launch {
            getCardUseCase.invoke(Unit)
                .onSuccess { response ->
                    response.collect {
                        setState { copy(card = it) }
                    }
                }
        }
    }

    override fun observeAction(action: Action) {
        when (action) {
            is UpdateCardVisibility -> updateCardVisibility(action.isCardOpened)
        }
    }

    private fun updateCardVisibility(isCardOpened: Boolean) {
        viewModelScope.launch {
            setState { copy(isCardOpened = isCardOpened) }
        }
    }
}
