package com.kiljae.mylistcollection.model

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PositionalDataSource
import com.kiljae.mylistcollection.common.data.DataDefault


class PagingDefaultDataSource private constructor(): ItemKeyedDataSource<Int, DataDefault>(){

    class Factory: DataSource.Factory<Int, DataDefault>(){
        override fun create(): DataSource<Int, DataDefault> {
            return PagingDefaultDataSource()
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<DataDefault>
    ) {
        callback.onResult(createItems(0, params.requestedLoadSize))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<DataDefault>) {
        callback.onResult(createItems(params.key, params.requestedLoadSize))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<DataDefault>) {

    }

    override fun getKey(item: DataDefault): Int {
        return item.index
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