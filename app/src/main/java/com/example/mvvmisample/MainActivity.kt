package com.example.mvvmisample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel
    var successfulApiCallHappened: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        if (savedInstanceState != null) {
            if (!savedInstanceState.getBoolean("successfulApiCallHappened", false))
                mainActivityViewModel.callApi()
        } else {
            mainActivityViewModel.callApi()
        }

        // observe only one type of data which is accumulation of
        // error and success response and render UI according to scenario

        mainActivityViewModel.getDataForView().observe(this, object : Observer<MainViewModelResponseModel> {

            override fun onChanged(response: MainViewModelResponseModel?) {
                response?.let {
                    if (response.documentList != null) {
                        successfulApiCallHappened = true
                        Log.i(MainActivity::class.java.simpleName, response.documentList.toString())
                    } else {
                        Log.i(MainActivity::class.java.simpleName, response.errorResponse.toString())
                    }
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putBoolean("successfulApiCallHappened", successfulApiCallHappened)
        super.onSaveInstanceState(outState)
    }
}
