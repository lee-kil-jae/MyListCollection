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
import com.kiljae.mylistcollection.databinding.ItemMultiPagingBinding
import com.kiljae.mylistcollection.viewmodel.MultiPagingViewModel

class ListAdapterMultiPaging(var vm : MultiPagingViewModel)
    : PagedListAdapter<DataDefault, RecyclerView.ViewHolder>(DIFF_CALLBACK){

    companion object{
        val HEADER = 0
        val ITEM = 1

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
    class ItemViewHolder(val binding: ItemMultiPagingBinding) : RecyclerView.ViewHolder(binding.root)

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
                    R.layout.item_multi_paging,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == ITEM){
            (holder as ItemViewHolder).binding.run {
                data = getItem(position-1)
                vmMultiPaging = vm
            }
        }
    }

}