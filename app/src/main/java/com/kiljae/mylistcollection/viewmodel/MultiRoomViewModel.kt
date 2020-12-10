package com.kiljae.mylistcollection.viewmodel

import androidx.lifecycle.MutableLiveData
import com.kiljae.mylistcollection.common.data.DataDefault

class MultiRoomViewModel : BaseViewModel() {
    companion object{
        val TAG = "MultiRoomViewModel"
    }

    val clickItem : MutableLiveData<DataDefault> = MutableLiveData()

    fun onClickItem(data: DataDefault){
        clickItem.postValue(data)
    }
}