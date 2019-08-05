package com.example.mvvmisample

import android.util.Log
import com.example.mvvmisample.Retro.RetroClass
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


//make Datasource operations here and send callback to repository via RxBus or Interface or SingleLiveEvent

class MainActivityRepository(val onApiCall: onApiCall) {

    val apiKey = "dvmKzGHRR2DQusQB4zQhk39BqZVASzAC";    //success token
    //val apiKey = "dvmKzGHRR2DQusQB4zQhk39BqZVASzA";   //unauthorized token

    fun callApi() {

        DisposableManager.add(
            RetroClass.getApiService().getArticles("election", apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { articleResponseModel ->
                        Log.i(
                            MainActivityRepository::class.java.simpleName,
                            "from api success----->\n" + articleResponseModel.toString()
                        )
                        if (articleResponseModel?.body() != null) {
                            onApiCall.onApiSuccess(articleResponseModel.body()!!.response.docs)
                        } else {
                            onApiCall.onApiFailure(null, articleResponseModel)
                        }
                    },
                    { error ->
                        Log.e(MainActivityRepository::class.java.simpleName, "from apierror-->\n" + error.message)
                        onApiCall.onApiFailure(error, null)
                    }
                )
        )
    }
}