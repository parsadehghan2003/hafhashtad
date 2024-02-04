package com.ftpd.hafhashtad.base.models

data class PostData(
    var id:Int = 0,
    var title: String = "",
    var description: String = "",
    var price: String = "",
    var image: String = "",
    var category: String = "",
    var rating: PostRate? = null
)

data class PostRate(
    var rate: Float = 0f,
    var count:Long = 0
)

data class PostsObject(
    val list: MutableList<PostData> = mutableListOf()
)