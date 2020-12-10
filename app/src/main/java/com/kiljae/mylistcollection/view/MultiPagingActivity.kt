package com.kiljae.mylistcollection.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kiljae.mylistcollection.R
import com.kiljae.mylistcollection.databinding.ActivityMultiPagingBinding
import com.kiljae.mylistcollection.model.ListAdapterMultiPaging
import kotlinx.android.synthetic.main.activity_multi_paging.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MultiPagingActivity: BaseActivity<ActivityMultiPagingBinding>() {
    companion object{
        val TAG = "MultiPagingActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_multi_paging


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vmMultiPaging = getViewModel()
        viewDataBinding.lifecycleOwner = this

        initObserve(viewDataBinding.vmMultiPaging)

        viewDataBinding.vmMultiPaging?.clickItem?.observe(this, Observer {
            Toast.makeText(this,
                "Index: ${it.index}, Desc: ${it.desc}",
                Toast.LENGTH_SHORT)
                .show()
        })

        viewDataBinding.vmMultiPaging?.let {vm->
            rcvMultiPaging.adapter = ListAdapterMultiPaging(vm)
            vm.load()?.observe(this, Observer {pagedList->
                (rcvMultiPaging.adapter as ListAdapterMultiPaging).submitList(pagedList)
            })
        }
    }
}