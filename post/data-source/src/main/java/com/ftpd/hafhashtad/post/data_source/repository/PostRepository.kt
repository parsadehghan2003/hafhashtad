package com.ftpd.hafhashtad.post.data_source.repository

import com.ftpd.hafhashtad.base.BaseDomain
import com.ftpd.hafhashtad.base.DataState

interface PostRepository {
    suspend fun getPosts(baseDomain: BaseDomain): DataState<BaseDomain>
}