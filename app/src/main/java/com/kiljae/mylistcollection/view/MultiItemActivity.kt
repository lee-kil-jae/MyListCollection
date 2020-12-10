package com.kiljae.mylistcollection.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kiljae.mylistcollection.R
import com.kiljae.mylistcollection.databinding.ActivityMultiItemBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MultiItemActivity: BaseActivity<ActivityMultiItemBinding>() {
    companion object{
        val TAG = "MultiItemActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_multi_item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vmMultiItem = getViewModel()
        viewDataBinding.lifecycleOwner = this

        initObserve(viewDataBinding.vmMultiItem)

        viewDataBinding.vmMultiItem?.clickItem?.observe(this, Observer {
            Toast.makeText(this,
                "Index: ${it.index}, Desc: ${it.desc}",
                Toast.LENGTH_SHORT)
                .show()
        })
    }
}