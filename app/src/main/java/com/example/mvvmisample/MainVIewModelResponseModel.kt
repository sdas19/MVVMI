package com.example.mvvmisample

import com.example.mvvmisample.Retro.ArticleResponseModel
import com.example.mvvmisample.Retro.DocumentModel
import retrofit2.Response

class MainViewModelResponseModel (val documentList : List<DocumentModel>?,val errorResponse : Response<ArticleResponseModel>?)