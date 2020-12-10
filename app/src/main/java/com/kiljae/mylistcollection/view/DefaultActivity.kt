package com.kiljae.mylistcollection.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kiljae.mylistcollection.R
import com.kiljae.mylistcollection.databinding.ActivityDefaultBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DefaultActivity: BaseActivity<ActivityDefaultBinding>() {
    companion object{
        val TAG = "DefaultActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vmDefault = getViewModel()
        viewDataBinding.lifecycleOwner = this

        initObserve(viewDataBinding.vmDefault)

        viewDataBinding.vmDefault?.clickItem?.observe(this, Observer {
            Toast.makeText(this,
                "Index: ${it.index}, Desc: ${it.desc}",
                Toast.LENGTH_SHORT)
                .show()
        })
    }
}