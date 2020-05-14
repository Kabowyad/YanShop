package com.example.yanshop.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity() {
    private val tag = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }
}

val AppCompatActivity.sharedPreferences: SharedPreferences
    get() = getSharedPreferences("data", Context.MODE_PRIVATE)
