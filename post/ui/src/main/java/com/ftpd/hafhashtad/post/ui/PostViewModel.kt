package com.ftpd.hafhashtad.post.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ftpd.hafhashtad.base.BaseDomain
import com.ftpd.hafhashtad.base.DataState
import com.ftpd.post.usecase.GetPostsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsInteractor: GetPostsInteractor
) : ViewModel() {
    val postsLiveData = MutableLiveData<DataState<BaseDomain>>()

    init {
    }

    fun getPostList(baseDomain: BaseDomain) {
        viewModelScope.launch {
            getPostsInteractor.call(baseDomain).onEach {
                postsLiveData.value = it
            }.launchIn(viewModelScope)
        }

    }

}