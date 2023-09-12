package com.productstesttask.domain.repository

import com.productstesttask.data.remote.models.toModel
import com.productstesttask.data.remote.service.IProductService
import com.productstesttask.data.remote.service.base.Answer
import com.productstesttask.data.remote.service.base.map
import com.productstesttask.domain.models.Product
import javax.inject.Inject

interface IProductsRepository {
    suspend fun getProducts(): Answer<List<Product>>
    suspend fun getParticularProduct(productName: String): Answer<Product>
}

class ProductsRepository @Inject constructor(
    private val service: IProductService
) : IProductsRepository {

    override suspend fun getProducts(): Answer<List<Product>> {
        return service.getProducts().map { response -> response.products.map { it.toModel() } }
    }

    override suspend fun getParticularProduct(productName: String): Answer<Product> {
        return service.getProducts().map { response ->
            response.products.find { it.title == productName }?.toModel() ?: throw NoSuchElementException("")
        }
    }
}
