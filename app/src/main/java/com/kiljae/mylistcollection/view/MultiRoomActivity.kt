package com.kiljae.mylistcollection.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kiljae.mylistcollection.R
import com.kiljae.mylistcollection.databinding.ActivityMultiRoomBinding
import com.kiljae.mylistcollection.model.ListAdapterMultiPaging
import com.kiljae.mylistcollection.model.ListAdapterMultiRoom
import com.kiljae.mylistcollection.model.room.MyDatabase
import kotlinx.android.synthetic.main.activity_multi_paging.*
import kotlinx.android.synthetic.main.activity_multi_room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class MultiRoomActivity: BaseActivity<ActivityMultiRoomBinding>() {
    companion object{
        val TAG = "MultiRoomActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_multi_room

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vmMultiRoom = getViewModel()
        viewDataBinding.lifecycleOwner = this

        initObserve(viewDataBinding.vmMultiRoom)

        viewDataBinding.vmMultiRoom?.clickItem?.observe(this, Observer {
            Toast.makeText(this,
                "Index: ${it.index}, Desc: ${it.desc}",
                Toast.LENGTH_SHORT)
                .show()
        })

        viewDataBinding.vmMultiRoom?.let {vm->
            GlobalScope.launch(Dispatchers.IO) {
                rcvMultiRoom.adapter = ListAdapterMultiRoom(MyDatabase.getInstance(this@MultiRoomActivity).myDao().getAll(), vm)
                (rcvMultiRoom.adapter as ListAdapterMultiRoom).notifyDataSetChanged()
            }
//            GlobalScope.launch(Dispatchers.Main) {
//                val items = async(Dispatchers.Default){
//                    MyDatabase.getInstance(this@MultiRoomActivity).myDao().getAll()
//                }
//                rcvMultiRoom.adapter = ListAdapterMultiRoom(items.await(), vm)
//                (rcvMultiRoom.adapter as ListAdapterMultiRoom).notifyDataSetChanged()
//            }
        }
    }
}