package com.productstesttask.domain.use_cases

import com.productstesttask.data.remote.service.base.Answer
import com.productstesttask.domain.models.Product
import com.productstesttask.domain.repository.CardRepository
import com.productstesttask.domain.use_cases.base.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteProductFromCardUseCase @Inject constructor(
    private val repo: CardRepository
) : UseCase<Product, Unit> {

    override suspend fun invoke(params: Product): Answer<Unit> {
        return withContext(Dispatchers.IO) {
            Answer.success(repo.deleteProductFromCard(params))
        }
    }
}
