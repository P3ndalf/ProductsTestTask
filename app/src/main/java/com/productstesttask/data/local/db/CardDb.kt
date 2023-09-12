package com.productstesttask.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.productstesttask.data.local.dao.CardDao
import com.productstesttask.data.local.models.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DbTypeConverter::class)
abstract class CardDb : RoomDatabase() {
    abstract fun cardDao(): CardDao

    companion object {
        private var INSTANCE: CardDb? = null

        fun getInstance(context: Context): CardDb {
            if (INSTANCE == null) {
                synchronized(CardDb::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        CardDb::class.java,
                        "card.db"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
