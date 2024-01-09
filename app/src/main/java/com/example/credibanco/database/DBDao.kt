package com.example.credibanco.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface DBDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrUpdate(entity: DBEntity)

}