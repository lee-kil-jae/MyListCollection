package com.kiljae.mylistcollection.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kiljae.mylistcollection.common.data.DataDefault

@Dao
interface MyDao {

    @Query("SELECT * FROM tableDataDefault")
    fun getAll(): List<DataDefault>

    @Insert
    fun insert(dataDefault: DataDefault)
}