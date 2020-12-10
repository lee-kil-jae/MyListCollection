package com.kiljae.mylistcollection.common.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "tableDataDefault")
@Parcelize
data class DataDefault(@PrimaryKey(autoGenerate = true) var index: Int = 0,
                       @ColumnInfo(name = "name") var title: String = "",
                       @ColumnInfo(name = "desc") var desc: String = ""): Parcelable {
    constructor() : this(-1, "", "")
}