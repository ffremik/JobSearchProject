package com.example.jobsearchproject.navigation

import com.example.jobsearchproject.R

data class BottomMenuItem(
    val iconRes: Int,
    val titleRes: Int,
    val route: String = "emptyActivity",
)

object ListItemsBottomMenu {
    val listItems = listOf (
        BottomMenuItem(R.drawable.icon_search, R.string.search, RouteNavigation.MAIN_ACTIVITY.route),
        BottomMenuItem(R.drawable.icon_favorites, R.string.favorites, RouteNavigation.FAVORITES_ACTIVITY.route),
        BottomMenuItem(R.drawable.icon_answers, R.string.answers),
        BottomMenuItem(R.drawable.icon_message, R.string.message),
        BottomMenuItem(R.drawable.icon_profile, R.string.profile),
    )
}