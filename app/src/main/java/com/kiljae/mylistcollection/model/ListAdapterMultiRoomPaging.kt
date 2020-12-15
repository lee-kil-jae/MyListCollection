package com.kiljae.mylistcollection.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kiljae.mylistcollection.R
import com.kiljae.mylistcollection.common.data.DataDefault
import com.kiljae.mylistcollection.databinding.AdMultiItemBinding
import com.kiljae.mylistcollection.databinding.HeaderMultiItemBinding
import com.kiljae.mylistcollection.databinding.ItemMultiRoomPagingBinding
import com.kiljae.mylistcollection.viewmodel.MultiRoomPagingViewModel

class ListAdapterMultiRoomPaging(var vm : MultiRoomPagingViewModel)
    : PagedListAdapter<DataDefault, RecyclerView.ViewHolder>(DIFF_CALLBACK){

    companion object{
        val HEADER = 0
        val ITEM = 1
        val AD = 2

        private val DIFF_CALLBACK: DiffUtil.ItemCallback<DataDefault> =
                object : DiffUtil.ItemCallback<DataDefault>() {
                    override fun areItemsTheSame(oldItem: DataDefault, newItem: DataDefault): Boolean {
                        return oldItem.index == newItem.index
                    }

                    override fun areContentsTheSame(oldItem: DataDefault, newItem: DataDefault): Boolean {
                        return oldItem.equals(newItem)
                    }
                }
    }

    class HeaderViewHolder(val binding: HeaderMultiItemBinding) : RecyclerView.ViewHolder(binding.root)
    class AdViewHolder(val binding: AdMultiItemBinding) : RecyclerView.ViewHolder(binding.root)
    class ItemViewHolder(val binding: ItemMultiRoomPagingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return if(position == 0){
            HEADER
        }else if(getItem(position)?.index?:-1 == -1){
            AD
        }else {
            ITEM
        }
//        return when(position){
//            0 -> HEADER
//            else -> ITEM
//        }
    }

//    override fun getItemCount(): Int {
//        return if(super.getItemCount() > 0){
//            super.getItemCount() + 1
//        }else{
//            0
//        }
////        return super.getItemCount() + 1
//    }

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
            AD -> AdViewHolder(
                DataBindingUtil.inflate(
                inflater,
                R.layout.ad_multi_item,
                parent,
                false
                )
            )
            else -> ItemViewHolder(
                    DataBindingUtil.inflate(
                            inflater,
                            R.layout.item_multi_room_paging,
                            parent,
                            false
                    )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            ITEM -> {
                (holder as ItemViewHolder).binding.run {
                    data = getItem(position)
                    vmMultiRoomPaging = vm
                }
            }
            AD -> {
                (holder as AdViewHolder).binding.run {
                    data = getItem(position)
                }
            }
        }
    }

}