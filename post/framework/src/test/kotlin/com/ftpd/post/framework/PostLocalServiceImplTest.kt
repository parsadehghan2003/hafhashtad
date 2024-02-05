package com.ftpd.post.framework

import com.ftpd.hafhashtad.base.models.PostData
import com.ftpd.hafhashtad.database.domain.RealmPost
import io.realm.kotlin.types.RealmList

import com.ftpd.hafhashtad.base.DataState
import com.ftpd.hafhashtad.base.error_handler.dataStateInternalErrorHandler
import com.ftpd.hafhashtad.database.data_source.service.PostDataStorage
import com.ftpd.hafhashtad.database.domain.RealmPostRate
import com.ftpd.hafhashtad.post.data_source.service.PostLocalService
import com.ftpd.hafhashtad.post.domain.GetPostsObject
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class PostLocalServiceImplTest {

    // Mock dependencies
    private val postDataStorage: PostDataStorage = mock(PostDataStorage::class.java)

    // Class under test
    private val postLocalService: PostLocalService = PostLocalServiceImpl(postDataStorage)

    @Test
    fun `readPosts with non-null realmList should return DataState with GetPostsObject`() =
        runBlocking {
            val mockRealmPost: RealmPost = RealmPost().apply {
                id = 1
                title = "realmObject.title"
                price = "12"
                description = "realmObject.description"
                image = "realmObject.image"
                category = "realmObject.category"

                rating = RealmPostRate().apply {
                    rate = 5.0f
                    rateCount = 323
                }
            }
            val mockRealmList: RealmList<RealmPost> = realmListOf(mockRealmPost)
            for (i in 1..50){
                mockRealmList.add(mockRealmPost)
            }

            `when`(postDataStorage.readPosts()).thenReturn(mockRealmList)

            val result = postLocalService.readPosts()

            ((result as DataState.Data).data as GetPostsObject.GetPostsObjectResponse).posts.list.forEachIndexed { i, data ->
                val item = createPostsObjectWithPostRealmList(mockRealmList).list[i]
                assertEquals(item.id, data.id)
                assertEquals(item.title, data.title)
                assertEquals(item.price, data.price)
                assertEquals(item.description, data.description)
                assertEquals(item.image, data.image)
                assertEquals(item.category, data.category)
                assertEquals(item.rating?.rate, data.rating?.rate)
                assertEquals(item.rating?.count, data.rating?.count)
            }

        }

    @Test
    fun `readPosts with null realmList should return DataState with internal error`() =
        runBlocking {
            `when`(postDataStorage.readPosts()).thenReturn(null)

            val result = postLocalService.readPosts()

            assertEquals(dataStateInternalErrorHandler(1), result)
        }
}