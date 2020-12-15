package com.kiljae.mylistcollection.model

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import com.kiljae.mylistcollection.common.data.DataDefault
import com.kiljae.mylistcollection.model.room.MyDao
import com.kiljae.mylistcollection.model.room.MyDatabase
import kotlinx.android.synthetic.main.activity_multi_room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomPagingDefaultDataSource private constructor(val database: MyDao): ItemKeyedDataSource<Int, DataDefault>(){

    class Factory(val database: MyDao): DataSource.Factory<Int, DataDefault>(){
        override fun create(): DataSource<Int, DataDefault> {
            return RoomPagingDefaultDataSource(database)
        }
    }

    var itemKey = 0
    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<DataDefault>
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            val datas = database.getDatas(params.requestedInitialKey?:0, params.requestedLoadSize)
            datas.add(0, DataDefault(-1, "", ""))
            if(datas.size > 0) {
                itemKey = datas.get(datas.size - 1).index
            }
            if(datas.size == (params.requestedLoadSize+1)) {
                datas.add(DataDefault(-1, "광고 보고 가실께요.[${itemKey}]"))
            }
            callback.onResult(datas)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<DataDefault>) {
        GlobalScope.launch(Dispatchers.IO) {
            val datas = database.getDatas(params.key, params.requestedLoadSize)
            if(datas.size > 0) {
                itemKey = datas.get(datas.size - 1).index
            }
            if(datas.size == (params.requestedLoadSize)) {
                datas.add(DataDefault(-1, "광고 보고 가실께요.[${itemKey}]"))
            }
            callback.onResult(datas)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<DataDefault>) {

    }

    override fun getKey(item: DataDefault): Int {
        return itemKey
    }


    private fun createItems(offset: Int, loadSize: Int): List<DataDefault>{
        val items = mutableListOf<DataDefault>()
        for(idx in offset .. (offset+loadSize-1)){
            items.add(
                    DataDefault(
                            idx+1,
                            "Title ${idx+1}",
                            "Description blabla umum..."
                    )
            )
        }

        return items
    }

}