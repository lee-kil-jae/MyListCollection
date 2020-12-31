package com.kiljae.mylistcollection.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kiljae.mylistcollection.R
import com.kiljae.mylistcollection.common.data.DataDefault
import com.kiljae.mylistcollection.model.room.MyDatabase
import kotlinx.android.synthetic.main.activity_main.*
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

//        Log.d(TAG, "Main Thread - Start")
//        Thread(Runnable {
//            for( i in 0..5){
//                Thread.sleep(1000L)
//                Log.d(TAG, "Working in Thread[${i}]")
//            }
//        }).start()
//        Log.d(TAG, "Main Thread - End")
//
//        Log.d(TAG, "Main Thread - Start")
//        GlobalScope.launch {
//            repeat(5){
//                delay(2000L)
//                Log.d(TAG, "Working in Coroutine.")
//            }
//        }
//        Log.d(TAG, "Main Thread - End")

//        Log.d(TAG, "Main Thread - Start")
//        runBlocking {
//            val job1 = launch {
//                delay(2000L)
//                Log.d(TAG, "launch #1")
//            }
//
//            job1.join()
//            coroutineScope {
//                val job2 = launch {
//                    delay(1000L)
//                    Log.d(TAG, "launch #2")
//                }
//                job2.join()
//                Log.d(TAG, "end coroutineScope")
//            }
//
//            Log.d(TAG, "end block")
//        }
//
//        Log.d(TAG, "Main Thread - End")

//        Log.d(TAG, "Main Thread - Start")
//        runBlocking {
//            delay(1000L)
//            Log.d(TAG, "Working in Coroutine.")
//        }
//        Log.d(TAG, "Main Thread - End")

//        Log.d(TAG, "Main Thread - Start")
//
//        runBlocking {
//            val job = launch {
//                delay(1000L)
//                Log.d(TAG, "launch #1")
//            }
//            job.join()
//            Log.d(TAG, "end Block")
//        }
//
//        Log.d(TAG, "Main Thread - End")

//        Log.d(TAG, "Main Thread - Start")
//        runBlocking {
//            val job = launch {
//                repeat(100) {
//                    delay(200L)
//                    Log.d(TAG, "launch #${it}")
//                }
//            }
//            delay(500L)
//            job.cancelAndJoin()
//            Log.d(TAG, "end Block")
//        }
//        Log.d(TAG, "Main Thread - End")

//        Log.d(TAG, "Main Thread - Start")
//        GlobalScope.launch {
//            Log.d(TAG, "Result Value is ")
//            val value = async {
//                var total = 0
//                for(i in 0 ..10){
//                    total += i
//                    delay(200L)
//                }
//                total
//            }
//            Log.d(TAG, "${value.await()}")
//        }
//        Log.d(TAG, "Main Thread - End")

//        Log.d(TAG, "Main Thread - Start")
//        runBlocking {
//            val job1 = launch {
//                withTimeout(500L) {
//                    repeat(100) {
//                        Log.d(TAG, "launch #${it}")
//                        delay(100L)
//                    }
//                }
//            }
//
//            delay(2000L)
//            job1.cancelAndJoin()
//            Log.d(TAG, "end Block")
//        }
//        Log.d(TAG, "Main Thread - End")

//        Log.d(TAG, "Main Thread - Start")
//        GlobalScope.launch {
//            val value1 = async(start = CoroutineStart.LAZY) {
//                Log.d(TAG, "async #1")
//                100
//            }
//
//            val value2 = async(start = CoroutineStart.LAZY) {
//                Log.d(TAG, "async #2")
//                200
//            }
//
//            value2.start()
//            value1.start()
//
//            Log.d(TAG, "${value1.await()}")
//            Log.d(TAG, "${value2.await()}")
//        }
//        Log.d(TAG, "Main Thread - End")

//        Log.d(TAG, "Main Thread - Start")
//        runBlocking {
//            val value = doSomething()
//            Log.d(TAG, "${value}")
//        }
//        Log.d(TAG, "Main Thread - End")

        Log.d(TAG, "Main Thread - Start")
        runBlocking {
            val channel = Channel<Int>()

            launch {
                for(i in 0 ..4){
                    delay(1000L)
                    channel.send(i)
                }
                channel.close()
            }

            for(ch in channel){
                Log.d(TAG, "channel value: ${ch}")
            }
        }
        Log.d(TAG, "Main Thread - End")

    }


//    suspend fun doSomething(): Int {
//        Log.d(TAG, "doSomething")
//        val value = GlobalScope.async(Dispatchers.IO) {
//            var total = 0
//            for(i in 0..10){
//                total += i
//            }
//            total
//        }
//        return value.await()
//    }
}