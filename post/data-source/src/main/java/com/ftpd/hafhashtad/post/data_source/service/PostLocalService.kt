package com.ftpd.hafhashtad.post.data_source.service

import com.ftpd.hafhashtad.base.BaseDomain
import com.ftpd.hafhashtad.base.DataState

interface PostLocalService {
    suspend fun readPosts(): DataState<BaseDomain>
    suspend fun insertPosts(baseDomain: BaseDomain): DataState<BaseDomain>

}