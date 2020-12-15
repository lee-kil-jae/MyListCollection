package com.kiljae.mylistcollection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kiljae.mylistcollection.common.data.DataDefault
import com.kiljae.mylistcollection.model.RoomPagingDefaultDataSource
import com.kiljae.mylistcollection.model.room.MyDao

class MultiRoomPagingViewModel(database: MyDao) : BaseViewModel() {
    companion object{
        val TAG = "MultiRoomPagingViewModel"
    }

    val clickItem : MutableLiveData<DataDefault> = MutableLiveData()

    val config: PagedList.Config
    val pagedListBuilder : LivePagedListBuilder<Int, DataDefault>

    init{
        config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(20)
                .setPrefetchDistance(5)
                .setPageSize(20)
                .build()

        pagedListBuilder = LivePagedListBuilder(RoomPagingDefaultDataSource.Factory(database), config)
    }

    fun load(): LiveData<PagedList<DataDefault>> {
        return pagedListBuilder.setInitialLoadKey(0).build()
    }

    fun onClickItem(data: DataDefault){
        clickItem.postValue(data)
    }
}