package com.ftpd.hafhashtad.post.domain

import com.ftpd.hafhashtad.base.ActionType
import com.ftpd.hafhashtad.base.BaseDomain
import com.ftpd.hafhashtad.base.models.PostsObject

sealed class GetPostsObject : BaseDomain {

    object GetPostsObjectRequest : GetPostsObject() {
        override val actionType: ActionType
            get() = ActionType.GET_POSTS_REQUEST
    }

    data class GetPostsObjectResponse(
        val posts: PostsObject,
    ) : GetPostsObject() {
        override val actionType: ActionType
            get() = ActionType.GET_POSTS_RESPONSE

    }
}
