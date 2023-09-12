package com.productstesttask.domain.repository

import com.productstesttask.data.local.dao.CardDao
import com.productstesttask.data.local.models.toModel
import com.productstesttask.domain.models.Product
import com.productstesttask.domain.models.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ICardRepository {
    suspend fun getProductsInCard(): Flow<List<Product>>
    suspend fun insertProductInCard(product: Product)
    suspend fun deleteProductFromCard(product: Product)
}

class CardRepository @Inject constructor(
    private val dao: CardDao
) : ICardRepository {

    override suspend fun getProductsInCard(): Flow<List<Product>> =
        dao.getProductsInCard().map { products ->
            products.map { entity -> entity.toModel() }
        }

    override suspend fun insertProductInCard(product: Product) {
        dao.insertProduct(product.toEntity())
    }

    override suspend fun deleteProductFromCard(product: Product) {
        dao.deleteProduct(product.toEntity())
    }
}
