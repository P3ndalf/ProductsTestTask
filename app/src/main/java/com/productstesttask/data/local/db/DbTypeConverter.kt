package com.productstesttask.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.productstesttask.data.local.models.ProductEntity

object DbTypeConverter {
    @TypeConverter
    fun productsToJson(productsEntities: List<ProductEntity>) = Gson().toJson(productsEntities)!!

    @TypeConverter
    fun productsFromJson(json: String): List<ProductEntity> =
        (Gson().fromJson(json, Array<ProductEntity>::class.java) ?: emptyArray()).toList()
}
