package com.productstesttask.domain.base.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Action : UiAction, State : UiState>(
    initialState: State
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _uiAction = MutableSharedFlow<Action>()
    private val uiAction = _uiAction.asSharedFlow()

    init {
        initActionsListener()
    }

    private fun initActionsListener() {
        viewModelScope.launch {
            _uiAction.collect { observeAction(it) }
        }
    }

    abstract fun observeAction(action: Action)

    fun setAction(action: Action) {
        viewModelScope.launch { _uiAction.emit(action) }
    }

    protected suspend fun setState(newState: State.() -> State) {
        _uiState.emit(uiState.value.newState())
    }
}