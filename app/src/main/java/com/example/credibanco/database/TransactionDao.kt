package com.example.credibanco.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.credibanco.database.TransactionEntity

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrUpdateRow(entity: TransactionEntity)

    @Query("SELECT * FROM `TRANSACTION`")
    fun getTransactions(): List<TransactionEntity>

    @Query("UPDATE `TRANSACTION` SET STATUS = :status WHERE RECEPTION_ID = :receiptId")
    fun updateStatus(receiptId: String, status: String)

}