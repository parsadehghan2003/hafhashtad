package com.ftpd.post.framework

import com.ftpd.hafhashtad.base.BaseDomain
import com.ftpd.hafhashtad.base.DataState
import com.ftpd.hafhashtad.base.error_handler.dataStateInternalErrorHandler
import com.ftpd.hafhashtad.database.data_source.service.PostDataStorage
import com.ftpd.hafhashtad.post.data_source.service.PostLocalService
import com.ftpd.hafhashtad.post.domain.GetPostsObject
import javax.inject.Inject

class PostLocalServiceImpl @Inject constructor(
    private val postDataStorage: PostDataStorage,
) : PostLocalService {
    override suspend fun readPosts(): DataState<BaseDomain> {
        val realmList = postDataStorage.readPosts()
        return if (realmList != null)
            DataState.Data(
                GetPostsObject.GetPostsObjectResponse(
                    createPostsObjectWithPostRealmList(realmList)
                )
            )
        else dataStateInternalErrorHandler(1)
    }

    override suspend fun insertPosts(baseDomain: BaseDomain): DataState<BaseDomain> {
        val realmList = postDataStorage.insertOrUpdatePosts(
            createPostRealmListWithPostsObject(
                (baseDomain as GetPostsObject.GetPostsObjectResponse).posts
            )
        )
        return if (realmList != null)
            DataState.Data(
                GetPostsObject.GetPostsObjectResponse(
                    createPostsObjectWithPostRealmList(realmList)
                )
            )
        else dataStateInternalErrorHandler(1)
    }
}