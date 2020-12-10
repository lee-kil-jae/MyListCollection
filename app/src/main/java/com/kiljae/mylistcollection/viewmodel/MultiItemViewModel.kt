package com.kiljae.mylistcollection.viewmodel

import androidx.lifecycle.MutableLiveData
import com.kiljae.mylistcollection.common.NotNullMutableLiveData
import com.kiljae.mylistcollection.common.data.DataDefault

class MultiItemViewModel: BaseViewModel() {
    companion object{
        val TAG = "MultiItemViewModel"
    }

    val items: NotNullMutableLiveData<MutableList<DataDefault>> = NotNullMutableLiveData(mutableListOf())
    val clickItem : MutableLiveData<DataDefault> = MutableLiveData()

    init{
        for(idx in 0 .. 9){
            items.value.run{
                add(
                    DataDefault(
                        idx+1,
                        "Title ${idx+1}",
                        "Description blabla umum..."
                    )
                )
            }
        }
    }

    fun onClickItem(data: DataDefault){
        clickItem.postValue(data)
    }
}