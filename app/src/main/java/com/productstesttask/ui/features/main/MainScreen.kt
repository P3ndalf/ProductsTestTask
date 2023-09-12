package com.productstesttask.ui.features.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.productstesttask.domain.base.navigation.Destination
import com.productstesttask.domain.components.ProductItem

@Composable
fun MainScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val state by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = state.products) { product ->
            ProductItem(
                product = product,
                onProductClick = { title -> navController.navigate(Destination.ProductDetail.build(title)) })
        }
    }
}