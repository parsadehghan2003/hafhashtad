package com.ftpd.hafhashtad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.ftpd.hafhashtad.base_android.util.AndroidUtilities
import com.ftpd.hafhashtad.navigator.DestinationFragment
import com.ftpd.hafhashtad.navigator.NavigationHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidUtilities.displayDensity = resources.displayMetrics.density

        NavigationHelper.navigateToDestination(
            DestinationFragment.POSTS_FRAGMENT,
            replace = false,
            addToBackStack = false,
        )
        lifecycleScope.launch {
            NavigationHelper.navigationFlow.collect { navigationModel ->
                Navigator.navigate(
                    R.id.nav_host_fragment,
                    supportFragmentManager,
                    navigationModel
                )
            }
        }
    }
}