package com.productstesttask.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.productstesttask.data.local.models.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Query("SELECT * FROM card_table")
    fun getProductsInCard(): Flow<List<ProductEntity>>

    @Insert
    fun insertProduct(product: ProductEntity)

    @Delete
    fun deleteProduct(product: ProductEntity)
}
