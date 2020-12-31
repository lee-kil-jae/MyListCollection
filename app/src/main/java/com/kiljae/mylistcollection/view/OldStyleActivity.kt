package com.kiljae.mylistcollection.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kiljae.mylistcollection.R
import com.kiljae.mylistcollection.common.data.DataDefault
import com.kiljae.mylistcollection.model.ListAdapterOldStyle
import kotlinx.android.synthetic.main.activity_oldstyle.*


class OldStyleActivity: AppCompatActivity() {
    companion object{
        val TAG = "OldStyleActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oldstyle)

        imvBack.setOnClickListener {
            this@OldStyleActivity.finish()
        }

        val items = mutableListOf<DataDefault>()
        for(idx in 0 .. 9){
            items.run{
                add(
                    DataDefault(
                        idx+1,
                        "Title ${idx+1}",
                        "Description blabla umum..."
                    )
                )
            }
        }

        rcvOldStyle.adapter = ListAdapterOldStyle(items){
            Toast.makeText(this@OldStyleActivity, "Item Click: ${it.index}", Toast.LENGTH_SHORT).show()
        }
        (rcvOldStyle.adapter as ListAdapterOldStyle).notifyDataSetChanged()

    }
}