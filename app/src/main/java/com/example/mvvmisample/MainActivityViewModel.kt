package com.example.mvvmisample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel


//Keep data ready for UI here, dont do business logic here

class MainActivityViewModel : ViewModel() {

    var mainActivityInteractor: MainActivityInteractor = MainActivityInteractor()

    fun callApi() {
        mainActivityInteractor.callApiToGetData();
    }

    fun getDataForView(): LiveData<MainViewModelResponseModel> {
        return mainActivityInteractor.getDataForViewModel()
    }
}