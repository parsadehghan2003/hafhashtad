package com.ftpd.post.framework

import com.ftpd.hafhashtad.base.BaseDomain
import com.ftpd.hafhashtad.base.DataState
import com.ftpd.hafhashtad.base.error_handler.dataStateRemoteErrorHandler
import com.ftpd.hafhashtad.base.models.PostsObject
import com.ftpd.hafhashtad.gateway.FakeApiService
import com.ftpd.hafhashtad.post.data_source.service.PostRemoteService
import com.ftpd.hafhashtad.post.domain.GetPostsObject
import javax.inject.Inject


class PostRemoteServiceImpl @Inject constructor(
    private val fakeApiService: FakeApiService,
) : PostRemoteService {
    override suspend fun getPosts(baseDomain: BaseDomain): DataState<BaseDomain> {
        return try {
            val response = fakeApiService.getPostList()
            if (response.isSuccessful)
                DataState.Data(response.body()?.let { GetPostsObject.GetPostsObjectResponse(
                    PostsObject(it.toMutableList())
                ) })
            else dataStateRemoteErrorHandler(response.code())
        } catch (exception: Exception) {
            dataStateRemoteErrorHandler(0)
        }

    }
}