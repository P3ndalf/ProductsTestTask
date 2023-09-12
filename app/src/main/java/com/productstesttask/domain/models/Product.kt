package com.productstesttask.domain.models

import com.productstesttask.data.local.models.ProductEntity
import com.productstesttask.domain.models.Status.OUT_OF_STOCK

data class Product(
    val title: String,
    val price: Int,
    val content: String,
    val status: Status
) {
    companion object {
        val DEFAULT = Product(
            title = "",
            price = 0,
            content = "",
            status = OUT_OF_STOCK
        )
    }
}

fun Product.toEntity() = ProductEntity(
    title = title,
    content = content,
    price = price,
    status = status.serverKey
)
