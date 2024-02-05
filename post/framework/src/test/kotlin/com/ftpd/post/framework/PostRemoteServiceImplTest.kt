import com.ftpd.hafhashtad.base.BaseDomain
import com.ftpd.hafhashtad.base.DataState
import com.ftpd.hafhashtad.base.error_handler.dataStateRemoteErrorHandler
import com.ftpd.hafhashtad.base.models.PostData
import com.ftpd.hafhashtad.post.data_source.service.PostRemoteService
import com.ftpd.hafhashtad.post.domain.GetPostsObject
import com.ftpd.hafhashtad.base.models.PostsObject
import com.ftpd.hafhashtad.gateway.FakeApiService
import com.ftpd.post.framework.PostRemoteServiceImpl
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class PostRemoteServiceImplTest {

    // Mock dependencies
    private val fakeApiService: FakeApiService = mock()

    // Class under test
    private val placeRemoteService: PostRemoteService = PostRemoteServiceImpl(fakeApiService)

    @Test
    fun `getPosts with successful response should return DataState with GetPostsObject`() = runBlocking {
        // Given
        val baseDomain: BaseDomain = GetPostsObject.GetPostsObjectRequest

        val postData : PostData = mock()
        val mockResponse: Response<List<PostData>> = Response.success(listOf(postData))
        whenever(fakeApiService.getPostList()).thenReturn(mockResponse)

        // When
        val result = placeRemoteService.getPosts(baseDomain)

        // Then
        assertEquals(mockResponse.body(), ((result as DataState.Data).data as GetPostsObject.GetPostsObjectResponse).posts.list)
    }
    @Test
    fun `getPosts with unsuccessful response should return appropriate error DataState`() = runBlocking {
        // Given
        val baseDomain: BaseDomain = mock()
        `when`(fakeApiService.getPostList()).thenReturn(Response.error(404, "Not Found".toResponseBody()))

        // When
        val result = placeRemoteService.getPosts(baseDomain)

        // Then
        assertEquals(dataStateRemoteErrorHandler(404), result)
    }

    @Test
    fun `getPosts with network error should return appropriate error DataState`() = runBlocking {
        // Given
        val baseDomain: BaseDomain = mock()
        whenever(fakeApiService.getPostList()).thenThrow()

        // When
        val result = placeRemoteService.getPosts(baseDomain)

        // Then
        assertEquals(dataStateRemoteErrorHandler(0), result)
    }
}