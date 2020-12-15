package com.kiljae.mylistcollection.common.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kiljae.mylistcollection.common.data.DataDefault.Companion.INDEX
import com.kiljae.mylistcollection.common.data.DataDefault.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize


@Entity(
        tableName = TABLE_NAME,
        indices = [Index(value = [INDEX])])
@Parcelize
data class DataDefault(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "index") var index: Int = 0,
                       @ColumnInfo(name = "title") var title: String = "",
                       @ColumnInfo(name = "desc") var desc: String = ""): Parcelable {
    constructor() : this(0, "", "")
    companion object{
        const val TABLE_NAME = "tableDataDefault"
        const val INDEX = "index"
    }
}