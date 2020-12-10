package com.kiljae.mylistcollection.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiljae.mylistcollection.R
import com.kiljae.mylistcollection.common.data.DataDefault
import com.kiljae.mylistcollection.model.room.MyDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var offset = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnListTypeDefault.setOnClickListener {
            startActivity(Intent(this, DefaultActivity::class.java))
        }

        btnListTypeMultiItem.setOnClickListener {
            startActivity(Intent(this, MultiItemActivity::class.java))
        }

        btnListTypeMultiPaging.setOnClickListener {
            startActivity(Intent(this, MultiPagingActivity::class.java))
        }

        btnRoomAdd.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                offset += 1
                MyDatabase.getInstance(this@MainActivity).myDao().insert(
                    DataDefault(
                        title = "TITLE ${offset}",
                        desc = "DESCRIPTION ${offset}"
                    )
                )
            }
        }

        btnListTypeMultiRoom.setOnClickListener {
            startActivity(Intent(this, MultiRoomActivity::class.java))
        }
    }
}