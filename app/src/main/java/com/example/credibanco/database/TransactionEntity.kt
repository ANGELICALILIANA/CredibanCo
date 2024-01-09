package com.example.credibanco.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TransactionEntity.TABLE_NAME)
class TransactionEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = NAME) var transactionId: String = "",
    @ColumnInfo(name = RECEPTION_ID) var receptiontId: String = "",
    @ColumnInfo(name = RRN) var rrn: String = "",
    @ColumnInfo(name = STATUS) var status: String = ""
) {
    companion object {
        const val TABLE_NAME = "TRANSACTION"
        const val NAME = "ID_TRANSACTION"
        const val RECEPTION_ID = "RECEPTION_ID"
        const val RRN = "RRN"
        const val STATUS = "STATUS"
    }
}


