package com.kiljae.mylistcollection.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kiljae.mylistcollection.common.data.DataDefault

@Dao
interface MyDao {

    @Query("SELECT * FROM tableDataDefault")
    fun getAll(): MutableList<DataDefault>

    @Query("SELECT * FROM tableDataDefault WHERE `index` > :offset LIMIT :limit")
    fun getDatas(offset: Int, limit: Int): MutableList<DataDefault>

    @Query("SELECT COUNT('index') FROM tableDataDefault")
    fun getCount(): Int

    @Insert
    fun insert(dataDefault: DataDefault)
}