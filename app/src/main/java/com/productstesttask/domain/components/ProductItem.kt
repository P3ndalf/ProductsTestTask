package com.productstesttask.domain.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.productstesttask.data.models.Product
import com.productstesttask.data.models.Status.AVAILABLE
import com.productstesttask.ui.theme.RECTANGLE_R16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    product: Product,
    onProductClick: (title: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RECTANGLE_R16,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(text = product.title)
                Text(text = product.price.toString())
            }

            Image(
                painter = painterResource(id = product.status.resId),
                contentDescription = null
            )

            IconButton(onClick = { onProductClick.invoke(product.title) }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = rememberVectorPainter(Icons.Rounded.ArrowForward),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
@Preview
fun ProductItemPreview() {
    ProductItem(product = Product("something", 1231, "lalalalala", AVAILABLE), onProductClick = { _ -> })
}