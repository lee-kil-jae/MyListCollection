package com.kiljae.mylistcollection.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kiljae.mylistcollection.R
import com.kiljae.mylistcollection.databinding.ActivityMultiRoomPagingBinding
import com.kiljae.mylistcollection.model.ListAdapterMultiRoomPaging
import kotlinx.android.synthetic.main.activity_multi_room_paging.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MultiRoomPagingActivity: BaseActivity<ActivityMultiRoomPagingBinding>() {
    companion object{
        val TAG = "MultiRoomPagingActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_multi_room_paging


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vmMultiRoomPaging = getViewModel()
        viewDataBinding.lifecycleOwner = this

        initObserve(viewDataBinding.vmMultiRoomPaging)

        viewDataBinding.vmMultiRoomPaging?.clickItem?.observe(this, Observer {
            Toast.makeText(this,
                    "Index: ${it.index}, Desc: ${it.desc}",
                    Toast.LENGTH_SHORT)
                    .show()
        })

        viewDataBinding.vmMultiRoomPaging?.let {vm->
            rcvMultiRoomPaging.adapter = ListAdapterMultiRoomPaging(vm)
            vm.load()?.observe(this, Observer {pagedList->
                (rcvMultiRoomPaging.adapter as ListAdapterMultiRoomPaging).submitList(pagedList)
            })
        }
    }
}