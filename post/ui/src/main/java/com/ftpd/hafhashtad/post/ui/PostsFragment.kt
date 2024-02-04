package com.ftpd.hafhashtad.post.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.ftpd.hafhashtad.base.DataState
import com.ftpd.hafhashtad.base.models.PostData
import com.ftpd.hafhashtad.base_android.adapter_utils.MarginItemDecoration
import com.ftpd.hafhashtad.base_android.extentions.addView
import com.ftpd.hafhashtad.base_android.extentions.createFrame
import com.ftpd.hafhashtad.base_android.extentions.dp
import com.ftpd.hafhashtad.base_android.extentions.matchParent
import com.ftpd.hafhashtad.base_android.util.LayoutHelper.Companion.createLinear
import com.ftpd.hafhashtad.post.domain.GetPostsObject
import com.ftpd.hafhashtad.resource.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private val postViewModel: PostViewModel by viewModels()
    private lateinit var rootView: LinearLayout
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var topAppBar: AppBarLayout
    private lateinit var toolbar: MaterialToolbar
    private lateinit var searchView: SearchView
    var placesList: MutableList<PostData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postViewModel.getPostList(GetPostsObject.GetPostsObjectRequest)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = LinearLayout(requireContext())
        rootView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        rootView.orientation = LinearLayout.VERTICAL

        topAppBar = AppBarLayout(requireContext())
        topAppBar.elevation = 0f
        addView(
            rootView,
            topAppBar,
            createLinear(matchParent, 56, gravity = Gravity.TOP)
        )
        toolbar = MaterialToolbar(
            ContextThemeWrapper(
                requireContext(),
                com.google.android.material.R.style.Widget_MaterialComponents_Toolbar
            )
        )

        toolbar.setBackgroundColor(Color.WHITE)

        topAppBar.outlineProvider = null
        toolbar.title = null
        val menu = toolbar.menu
        val item = menu.add(Menu.NONE, R.id.countryFragmentIconSearch, Menu.NONE, "search")
        item.setIcon(R.drawable.ic_search)
        searchView = SearchView(requireContext())
        item.actionView = searchView
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { Log.e("TAG", it) }
                if (!query.isNullOrEmpty()) {
                    postsAdapter.filter.filter(query)

                } else {
                    postsAdapter.submitList(placesList)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { Log.e("TAG", it) }
                if (!newText.isNullOrEmpty()) {
                    postsAdapter.filter.filter(newText)

                } else {
                    postsAdapter.submitList(placesList)


                }
                return false

            }

        })
        addView(
            topAppBar,
            toolbar,
            createLinear(
                matchParent,
                56
            )
        )
        recyclerView = RecyclerView(requireContext())
        recyclerView.id = R.id.countryRecyclerViewId
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        postsAdapter = PostsAdapter(requireContext())
        recyclerView.adapter = postsAdapter
        recyclerView.addItemDecoration(
            MarginItemDecoration(12.dp())
        )
        addView(
            parentView = rootView,
            childView = recyclerView,
            createFrame(width = matchParent, height = matchParent)
        )
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postViewModel.postsLiveData.observe(
            viewLifecycleOwner
        ) {
            when (it) {
                is DataState.Data -> {
                    val response = it.data as GetPostsObject.GetPostsObjectResponse
                    placesList = response.posts.list
                    postsAdapter.submitList(placesList)
                }

                is DataState.Error -> {

                }
            }
        }
    }

}