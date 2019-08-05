package com.example.mvvmisample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.mvvmisample.Retro.ArticleResponseModel
import com.example.mvvmisample.Retro.DocumentModel
import retrofit2.Response

//make Business logic here after getting raw data from Api and send to viewModel

class MainActivityInteractor : onApiCall {


    var mainActivityRepository: MainActivityRepository = MainActivityRepository(this)
    var dataForViewModel: MutableLiveData<MainViewModelResponseModel> = MutableLiveData()

    fun callApiToGetData() {
        mainActivityRepository.callApi()
    }

    fun getDataForViewModel(): LiveData<MainViewModelResponseModel> {
        return dataForViewModel
    }

    override fun onApiSuccess(articleList: List<DocumentModel>) {
        Log.i(MainActivityInteractor::class.java.simpleName, articleList.toString())
        dataForViewModel.value = MainViewModelResponseModel(articleList, null)
    }

    override fun onApiFailure(networkError: Throwable?, errorResponse: Response<ArticleResponseModel>?) {
        Log.i(MainActivityInteractor::class.java.simpleName, errorResponse.toString())
        dataForViewModel.value = MainViewModelResponseModel(null, errorResponse)
    }
}