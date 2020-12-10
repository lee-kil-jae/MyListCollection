package com.kiljae.mylistcollection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kiljae.mylistcollection.common.NotNullMutableLiveData
import com.kiljae.mylistcollection.common.data.DataDefault
import com.kiljae.mylistcollection.model.PagingDefaultDataSource

class MultiPagingViewModel : BaseViewModel() {
    companion object{
        val TAG = "MultiPagingViewModel"
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

        pagedListBuilder = LivePagedListBuilder(PagingDefaultDataSource.Factory(), config)
    }

    fun load(): LiveData<PagedList<DataDefault>> {
        return pagedListBuilder.setInitialLoadKey(0).build()
    }

    fun onClickItem(data: DataDefault){
        clickItem.postValue(data)
    }
}