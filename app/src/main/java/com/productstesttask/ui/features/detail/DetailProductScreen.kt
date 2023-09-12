package com.productstesttask.ui.features.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.productstesttask.R
import com.productstesttask.ui.features.detail.Action.ChangeCard
import com.productstesttask.ui.features.detail.Action.GetProduct
import com.productstesttask.ui.theme.RECTANGLE_R16

@Composable
fun DetailProductScreen(
    navController: NavController,
    productTitle: String
) {

    val viewModel = hiltViewModel<DetailProductViewModel>()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(productTitle) {
        viewModel.setAction(GetProduct(productTitle))
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = state.product.title,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = state.product.content,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = state.product.price.toString(),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.DarkGray
                )
            )
        }

        Button(
            modifier = Modifier
                .clip(RECTANGLE_R16)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (state.isAddedToCard) Color.Red.copy(alpha = 0.25f)
                else Color.Green.copy(alpha = 0.75f),
                contentColor = Color.White
            ),
            shape = RECTANGLE_R16,
            onClick = { viewModel.setAction(ChangeCard(!state.isAddedToCard)) },
        ) {
            Text(
                text = stringResource(
                    id = if (state.isAddedToCard) R.string.remove_from_card_product
                    else R.string.add_to_card_product
                )
            )
        }
    }
}
