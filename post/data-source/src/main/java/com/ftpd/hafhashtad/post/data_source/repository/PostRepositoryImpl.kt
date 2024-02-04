package com.ftpd.hafhashtad.post.data_source.repository

import com.ftpd.hafhashtad.base.BaseDomain
import com.ftpd.hafhashtad.base.DataState
import com.ftpd.hafhashtad.post.data_source.service.PostLocalService
import com.ftpd.hafhashtad.post.data_source.service.PostRemoteService
import com.ftpd.hafhashtad.post.domain.GetPostsObject

class PostRepositoryImpl(
    private val placeLocalService: PostLocalService,
    private val placeRemoteService: PostRemoteService
) : PostRepository {
    override suspend fun getPosts(baseDomain: BaseDomain): DataState<BaseDomain> {
        return when (val getPlaces = placeRemoteService.getPosts(baseDomain)) {
            is DataState.Data -> {
                val getPlacesResponse = getPlaces.data as GetPostsObject.GetPostsObjectResponse
                placeLocalService.insertPosts(getPlacesResponse)
                placeLocalService.readPosts()
            }

            is DataState.Error -> {
                placeLocalService.readPosts()
            }
        }
    }

}