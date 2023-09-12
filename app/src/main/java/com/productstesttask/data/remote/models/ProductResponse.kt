package com.productstesttask.data.remote.models

import com.google.gson.annotations.SerializedName
import com.productstesttask.domain.models.Product
import com.productstesttask.domain.models.Status

data class ProductsResponse(
    @SerializedName("items") val products: List<ProductResponse>
)

data class ProductResponse(
    @SerializedName("name") val title: String? = null,
    @SerializedName("price") val price: Int? = null,
    @SerializedName("content") val content: String? = null,
    @SerializedName("status") val status: String? = null
)

fun ProductResponse.toModel() = Product(
    title = title.orEmpty(),
    price = price ?: 0,
    content = content.orEmpty(),
    status = Status getFromString status.orEmpty()
)
