package com.ftpd.post.usecase

import com.ftpd.hafhashtad.base.BaseDomain
import com.ftpd.hafhashtad.base.DataState
import com.ftpd.hafhashtad.base.interactor.UseCase
import com.ftpd.hafhashtad.post.data_source.repository.PostRepository

class GetPostsInteractor(private val postRepository: PostRepository) : UseCase<BaseDomain>() {
    override suspend fun run(params: BaseDomain?): DataState<BaseDomain> {
        return postRepository.getPosts(params!!)
    }

}