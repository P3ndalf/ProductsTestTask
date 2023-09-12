package com.productstesttask.ui.features.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.productstesttask.domain.base.navigation.Destination
import com.productstesttask.domain.components.ProductItem
import com.productstesttask.domain.components.ProductsHeader
import com.productstesttask.ui.features.main.Action.UpdateCardVisibility

@Composable
fun MainScreen(navController: NavController) {

    val viewModel = hiltViewModel<MainViewModel>()
    val state by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            ProductsHeader(
                isCardOpened = state.isCardOpened,
                onCheckedChange = { isOpened -> viewModel.setAction(UpdateCardVisibility(isOpened)) }
            )
        }

        items(items = if (state.isCardOpened) state.card else state.products) { product ->
            ProductItem(
                product = product,
                onProductClick = { title -> navController.navigate(Destination.ProductDetail.build(title)) })
        }
    }
}
