package com.kiljae.mylistcollection.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.kiljae.mylistcollection.R
import com.kiljae.mylistcollection.common.data.DataDefault
import com.kiljae.mylistcollection.model.room.MyDatabase
import com.kiljae.mylistcollection.view.dialog.MyCustomDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_custom_yesno.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class MainActivity : AppCompatActivity() {
    companion object{
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnListTypeOld.setOnClickListener {
            startActivity(Intent(this, OldStyleActivity::class.java))
        }

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
                val offset = MyDatabase.getInstance(this@MainActivity).myDao().getCount() + 1
                MyDatabase.getInstance(this@MainActivity).myDao().insert(
                        DataDefault(
                                index = offset,
                                title = "TITLE ${offset}",
                                desc = "DESCRIPTION ${offset}"
                        )
                )
            }
        }

        btnListTypeMultiRoom.setOnClickListener {
            startActivity(Intent(this, MultiRoomActivity::class.java))
        }

        btnListTypeMultiRoomPaging.setOnClickListener {
            startActivity(Intent(this, MultiRoomPagingActivity::class.java))
        }

        btnCustomDialog.setOnClickListener {
            MyCustomDialog.Builder()
                    .context(this@MainActivity)
                    .setTitle("Custom")
                    .setMessage("My Custom dialog")
                    .setOnClickNo("NO", {
                        Toast.makeText(this@MainActivity, "No Button Click", Toast.LENGTH_SHORT).show()
                        it.dismiss()
                    })
                    .setOnClickYes("YES", {
                        Toast.makeText(this@MainActivity, "Yes Button Click", Toast.LENGTH_SHORT).show()
                        it.dismiss()
                    })
                    .setOnFinished {

                    }.build()
                    .show()
        }
    }

}