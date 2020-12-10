package com.kiljae.mylistcollection.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kiljae.mylistcollection.common.data.DataDefault
import com.kiljae.mylistcollection.model.ListAdapterMultiItem
import com.kiljae.mylistcollection.model.ListAdapterDefault
import com.kiljae.mylistcollection.model.ListAdapterMultiRoom
import com.kiljae.mylistcollection.viewmodel.MultiItemViewModel
import com.kiljae.mylistcollection.viewmodel.DefaultViewModel
import com.kiljae.mylistcollection.viewmodel.MultiRoomViewModel

object BindingAdapterRecyclerView {

    @BindingAdapter(value = ["baMultiRoomListItems", "baViewModel"], requireAll = true)
    @JvmStatic
    fun initMultiRoomListView(view : RecyclerView, items : List<DataDefault>, vm : MultiRoomViewModel){
        view.adapter?.run {
            if (this is ListAdapterMultiRoom) {
                this.items = items
                this.vm = vm
            }
        }?: run{
            ListAdapterMultiRoom(items, vm)
                .apply {
                    view.adapter = this
                }
        }
    }

    @BindingAdapter(value = ["baMultiItemListItems", "baViewModel"], requireAll = true)
    @JvmStatic
    fun initMultiItemListView(view : RecyclerView, items : List<DataDefault>, vm : MultiItemViewModel){
        view.adapter?.run {
            if (this is ListAdapterMultiItem) {
                this.items = items
                this.vm = vm
            }
        }?: run{
            ListAdapterMultiItem(items, vm)
                .apply {
                    view.adapter = this
                }
        }
    }

    @BindingAdapter(value = ["baDefaultListItems", "baViewModel"], requireAll = true)
    @JvmStatic
    fun initDefaultListView(view : RecyclerView, items : List<DataDefault>, vm : DefaultViewModel){
        view.adapter?.run {
            if (this is ListAdapterDefault) {
                this.items = items
                this.vm = vm
            }
        }?: run{
            ListAdapterDefault(items, vm)
                .apply {
                    view.adapter = this
                }
        }
    }
}