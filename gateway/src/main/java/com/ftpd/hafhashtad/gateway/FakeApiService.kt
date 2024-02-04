package com.ftpd.hafhashtad.gateway

import com.ftpd.hafhashtad.base.models.PostData
import com.ftpd.hafhashtad.base.models.PostsObject
import retrofit2.Response
import retrofit2.http.GET


interface FakeApiService {

    @GET("products")
    suspend fun getPostList(): Response<List<PostData>>

}