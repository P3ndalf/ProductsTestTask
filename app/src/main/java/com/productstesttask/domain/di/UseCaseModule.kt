package com.productstesttask.domain.di

import com.productstesttask.domain.models.Product
import com.productstesttask.domain.use_cases.DeleteProductFromCardUseCase
import com.productstesttask.domain.use_cases.GetCardUseCase
import com.productstesttask.domain.use_cases.GetProductUseCase
import com.productstesttask.domain.use_cases.GetProductsUseCase
import com.productstesttask.domain.use_cases.InsertProductInCardUseCase
import com.productstesttask.domain.use_cases.base.UseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetProductsUseCase(useCase: GetProductsUseCase): UseCase<Unit, List<Product>>

    @Binds
    fun bindGetProductUseCase(useCase: GetProductUseCase): UseCase<String, Product>

    @Binds
    fun bindGetCardUseCase(useCase: GetCardUseCase): UseCase<Unit, Flow<List<Product>>>

    @Binds
    fun bindUpdateCardUseCase(useCase: InsertProductInCardUseCase): UseCase<Product, Unit>

    @Binds
    fun bindDeleteProductFromCardUseCase(useCase: DeleteProductFromCardUseCase): UseCase<Product, Unit>
}
