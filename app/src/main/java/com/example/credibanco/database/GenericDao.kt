package com.example.credibanco.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface GenericDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrUpdateRow(entity: T)

}