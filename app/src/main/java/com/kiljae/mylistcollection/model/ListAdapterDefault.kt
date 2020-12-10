package com.kiljae.mylistcollection.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kiljae.mylistcollection.R
import com.kiljae.mylistcollection.common.BindingViewHolder
import com.kiljae.mylistcollection.common.data.DataDefault
import com.kiljae.mylistcollection.databinding.ItemDefaultBinding
import com.kiljae.mylistcollection.viewmodel.DefaultViewModel

class ListAdapterDefault(var items : List<DataDefault> = arrayListOf(), var vm : DefaultViewModel)
    : RecyclerView.Adapter<ListAdapterDefault.DefaultListViewHolder>(){

    class DefaultListViewHolder(view: View) : BindingViewHolder<ItemDefaultBinding>(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultListViewHolder {

        return DefaultListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_default,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: DefaultListViewHolder, position: Int) {
        holder.binding.data = items[position]
        holder.binding.vmDefault = vm
    }

}