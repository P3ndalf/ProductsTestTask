package com.productstesttask.data.remote.service

import com.productstesttask.data.remote.api.IProductsApi
import com.productstesttask.data.remote.models.ProductsResponse
import com.productstesttask.data.remote.service.base.Answer
import com.productstesttask.data.remote.service.base.BaseService
import javax.inject.Inject

interface IProductService {
    suspend fun getProducts(): Answer<ProductsResponse>
}

class ProductsService @Inject constructor(
    private val api: IProductsApi
) : IProductService, BaseService() {

    override suspend fun getProducts(): Answer<ProductsResponse> {
        return apiCall { api.getProducts() }
    }
}
