package com.example.corutin

import android.os.Bundle
import android.util.Log

import android.view.View

import androidx.appcompat.app.AppCompatActivity

import com.example.corutin.databinding.ActivityMainBinding
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    var number: Int = 0
    private var Tag = "Corutine"
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        CoroutineScope(Dispatchers.IO).launch {
       printFollowers()
        }

    }


    suspend fun getFollower(): Int {
        delay(1000)
        return 55
    }

    suspend fun printFollowers() {
        val job = CoroutineScope(Dispatchers.IO).async {
            getFollower()
        }
        Log.i(Tag, job.await().toString())
    }
}


/*
    /*  Basic Function */
    fun IncreasNum(view: View) {
        number++
        binding!!.textView.text = number.toString()
    }
    fun StartForLoop(view: View) {
       CoroutineScope(Dispatchers.IO).launch {
           for (i in 1..10000000000L) { }
       }
    }

      /* Corutine Scopoe And Context */
      fun ScopeContext(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            Log.i(Tag, "1-${Thread.currentThread().name}")
            /*
            GlobalScope, simply means the scope of this coroutines is the entire application.
             It will not get finished, if it is running in the background
             it won’t stop unless the whole application stop.
              */
        }
        MainScope().launch(Dispatchers.Main) {
            Log.i(Tag, "2-${Thread.currentThread().name}")
            /*
            it is connected with our main Activity
             */
        }

        CoroutineScope(Dispatchers.Default).launch {
            Log.i(Tag, "3-${Thread.currentThread().name}")
            /*
             coroutinesScope, It creates a new scope and does not complete until
              all children’s coroutines complete. So we are creating a scope,
              we are running coroutines and inside the scope, we can create other coroutines.
              This coroutine that starts here does not complete until
              all the inner coroutines complete as well.
             */
        }
    }

    /* Suspend function */
    fun suspendModifire(view: View) {
        CoroutineScope(Dispatchers.Main).launch {
          task1()
        }

        CoroutineScope(Dispatchers.Main).launch {
            task2()
        }
    }
    /*
    Suspend function is a function that could be started, paused, and resume.
    One of the most important points to remember about the suspend functions
     is that they are only allowed to be called from a coroutine
     or another suspend function.
     */
    suspend fun task1(){
          Log.i(Tag, "Task 1 Started")
          yield()   // define suspenction point
          Log.i(Tag, "Task 1 Closed")
      }
    suspend fun task2(){
        Log.i(Tag, "Task 2 Started")
        yield()     // define suspenction point
        Log.i(Tag, "Task 2 Closed")
    }


 */



