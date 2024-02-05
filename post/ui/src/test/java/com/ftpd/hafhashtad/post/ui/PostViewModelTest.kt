import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ftpd.hafhashtad.base.BaseDomain
import com.ftpd.hafhashtad.base.DataState
import com.ftpd.hafhashtad.base.error_handler.dataStateRemoteErrorHandler
import com.ftpd.hafhashtad.post.domain.GetPostsObject
import com.ftpd.hafhashtad.post.ui.PostViewModel
import com.ftpd.post.usecase.GetPostsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class PostViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var getPostsInteractor: GetPostsInteractor

    private lateinit var viewModel: PostViewModel

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup() {
        getPostsInteractor = mock()
        viewModel = PostViewModel(getPostsInteractor)
        Dispatchers.setMain(testDispatcher)

    }

    @Test
    fun `getPostList with success response should update postsLiveData`() = testScope.runBlockingTest {
        val baseDomain: BaseDomain = GetPostsObject.GetPostsObjectRequest
        val successResponse = DataState.Data<BaseDomain>(mock(GetPostsObject.GetPostsObjectResponse::class.java))
        `when`(getPostsInteractor.run(baseDomain)).thenReturn(successResponse)

        viewModel.getPostList(baseDomain)

        assertEquals(successResponse, viewModel.postsLiveData.value)
    }

    @Test
    fun `getPostList with error response should update postsLiveData`() = testScope.runBlockingTest {
        val baseDomain: BaseDomain = GetPostsObject.GetPostsObjectRequest
        val errorResponse = dataStateRemoteErrorHandler(404)
        `when`(getPostsInteractor.run(baseDomain)).thenReturn(errorResponse)

        viewModel.getPostList(baseDomain)

        assertEquals(errorResponse, viewModel.postsLiveData.value)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}
