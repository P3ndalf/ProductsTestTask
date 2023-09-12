package com.productstesttask.data.remote.api

import com.productstesttask.data.remote.models.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface IProductsApi {
    @GET("get_products")
    suspend fun getProducts(): Response<ProductsResponse>
}
