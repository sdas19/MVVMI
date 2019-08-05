package com.example.mvvmisample

import com.example.mvvmisample.Retro.ArticleResponseModel
import com.example.mvvmisample.Retro.DocumentModel
import retrofit2.Response

interface onApiCall {
    fun onApiSuccess(articleList: List<DocumentModel>)
    fun onApiFailure(networkError: Throwable?, errorResponse: Response<ArticleResponseModel>?)
}