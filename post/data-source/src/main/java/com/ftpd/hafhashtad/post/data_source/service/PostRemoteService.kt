package com.ftpd.hafhashtad.post.data_source.service

import com.ftpd.hafhashtad.base.BaseDomain
import com.ftpd.hafhashtad.base.DataState

interface PostRemoteService {
    suspend fun getPosts(baseDomain: BaseDomain): DataState<BaseDomain>
}