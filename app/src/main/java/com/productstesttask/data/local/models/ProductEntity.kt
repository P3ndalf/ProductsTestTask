package com.productstesttask.data.local.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.productstesttask.domain.models.Product
import com.productstesttask.domain.models.Status
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "card_table")
class ProductEntity(
    @PrimaryKey val title: String,
    val content: String,
    val price: Int,
    val status: String
) : Parcelable

fun ProductEntity.toModel() = Product(
    title = title,
    content = content,
    price = price,
    status = Status getFromString status
)
