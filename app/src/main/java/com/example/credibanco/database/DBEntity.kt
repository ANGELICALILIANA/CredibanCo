package com.example.credibanco.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DBEntity.TABLE_NAME)
class DBEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0
) {
    companion object {
        const val TABLE_NAME = "database"
    }
}


