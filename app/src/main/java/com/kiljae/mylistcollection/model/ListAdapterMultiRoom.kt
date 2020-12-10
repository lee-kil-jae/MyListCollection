package com.kiljae.mylistcollection.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kiljae.mylistcollection.R
import com.kiljae.mylistcollection.common.data.DataDefault
import com.kiljae.mylistcollection.databinding.HeaderMultiItemBinding
import com.kiljae.mylistcollection.databinding.ItemMultiRoomBinding
import com.kiljae.mylistcollection.viewmodel.MultiRoomViewModel

class ListAdapterMultiRoom(var items : List<DataDefault> = arrayListOf(), var vm : MultiRoomViewModel)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object{
        val HEADER = 0
        val ITEM = 1
    }

    class HeaderViewHolder(val binding: HeaderMultiItemBinding) : RecyclerView.ViewHolder(binding.root)
    class ItemViewHolder(val binding: ItemMultiRoomBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> HEADER
            else -> ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            HEADER -> HeaderViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.header_multi_item,
                    parent,
                    false
                )
            )
            else -> ItemViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.item_multi_room,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = items.size+1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == ITEM){
            (holder as ItemViewHolder).binding.run {
                data = items[position-1]
                vmMultiRoom = vm
            }
        }
    }

}