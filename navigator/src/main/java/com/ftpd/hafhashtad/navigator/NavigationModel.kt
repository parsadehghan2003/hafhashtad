package com.ftpd.hafhashtad.navigator

data class NavigationModel(
    val destinationFragment: DestinationFragment,
    val replace: Boolean,
    val addToBackStack : Boolean,
    val shouldBeVisible : Boolean = false,
    var arg: Map<String, Any>? = null
) {

}