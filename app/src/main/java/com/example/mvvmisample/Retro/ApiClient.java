package com.example.mvvmisample.Retro;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {

    @GET("/svc/search/v2/articlesearch.json")
    Single<Response<ArticleResponseModel>> getArticles(@Query("q") String q, @Query("api-key") String apiKey);

}


//https://api.openbrewerydb.org/breweries