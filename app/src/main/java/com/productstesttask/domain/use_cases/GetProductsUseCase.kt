package com.productstesttask.domain.use_cases

import com.productstesttask.data.remote.service.base.Answer
import com.productstesttask.domain.models.Product
import com.productstesttask.domain.repository.IProductsRepository
import com.productstesttask.domain.use_cases.base.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repo: IProductsRepository
) : UseCase<Unit, List<@JvmSuppressWildcards Product>> {

    override suspend fun invoke(params: Unit): Answer<List<Product>> {
        return withContext(Dispatchers.IO) {
            repo.getProducts()
        }
    }
}
