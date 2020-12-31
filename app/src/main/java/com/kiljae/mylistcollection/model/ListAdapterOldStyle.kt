package com.kiljae.mylistcollection.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.kiljae.mylistcollection.R
import com.kiljae.mylistcollection.common.data.DataDefault

class ListAdapterOldStyle(var items : List<DataDefault>, val onItemClick: ((DataDefault)->Unit)) : RecyclerView.Adapter<ListAdapterOldStyle.AdapterViewHolder>() {

    inner class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clLayout : ConstraintLayout = itemView.findViewById(R.id.clLayout)
        val txvIndex : TextView = itemView.findViewById(R.id.txvIndex)
        val txvTitle : TextView = itemView.findViewById(R.id.txvTitle)

        init {
            clLayout.setOnClickListener {
                if(adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick.invoke(items[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_oldstyle, parent, false)
        return AdapterViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.txvIndex.text = items[position].index.toString()
        holder.txvTitle.text = items[position].title
    }
}