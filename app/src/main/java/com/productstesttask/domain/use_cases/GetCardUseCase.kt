package com.productstesttask.domain.use_cases

import com.productstesttask.data.remote.service.base.Answer
import com.productstesttask.domain.models.Product
import com.productstesttask.domain.repository.CardRepository
import com.productstesttask.domain.use_cases.base.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetCardUseCase @Inject constructor(
    private val repo: CardRepository
) : UseCase<Unit, Flow<@JvmSuppressWildcards List<Product>>> {

    private val _card = MutableStateFlow(emptyList<Product>())
    private val card = _card.asStateFlow()

    init {
        initCardListener()
    }

    private fun initCardListener() {
        CoroutineScope(Dispatchers.IO).launch {
            repo.getProductsInCard().collect { products ->
                _card.emit(products)
            }
        }
    }

    override suspend fun invoke(params: Unit): Answer<Flow<List<Product>>> {
        return Answer.success(card)
    }
}
