package com.example.credibanco.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.credibanco.database.DBDao
import com.example.credibanco.database.DBEntity
import com.example.credibanco.database.TransactionDao
import com.example.credibanco.database.TransactionEntity

@Database(entities = [TransactionEntity::class, DBEntity::class], version = 1, exportSchema = false)
abstract class TransactionDataBase : RoomDatabase() {

    abstract fun getTransactionDao(): TransactionDao
    abstract fun getDBDao(): DBDao

    companion object {
        @Volatile
        private var INSTANCE: TransactionDataBase? = null

        fun getInstance(context: Context): TransactionDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransactionDataBase::class.java,
                    "TRANSACTION_DB"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}