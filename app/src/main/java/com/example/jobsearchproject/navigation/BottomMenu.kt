package com.example.jobsearchproject.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.jobsearchproject.R
import com.example.jobsearchproject.view.mainScreen.viewmodel.MainVM
import com.example.jobsearchproject.style.TabText

@Composable
@Preview(showBackground = true)
fun PreviewBottomMenu() {
    BottomMenu()
}

@Composable
fun BottomMenu(mainVM: MainVM = viewModel(factory = MainVM.factory)) {
    val listVacancy by mainVM.allListFavorites.collectAsState(initial = emptyList())
    val sizeFavorites = listVacancy.size
    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                //modifier = Modifier.height(105.dp),
                actions = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        ListItemsBottomMenu.listItems.forEach { itemMenu ->
                            IconBottomMenu(
                                itemMenu,
                                sizeFavorites = sizeFavorites
                            ) {
                                navHostController.navigate(it) {
                                    launchSingleTop = true
                                }
                            }
                        }
                    }
                }
            )
        }
    ) {
        NavigationHost(
            modifier = Modifier.padding(it),
            navHostController = navHostController,
            mainVM = mainVM,
        )
    }
}

@Composable
fun IconBottomMenu(
    menuItem: BottomMenuItem,
    sizeFavorites: Int,
    onClick: (String) -> Unit
) {
    val isCount = sizeFavorites > 0 && menuItem.route == RouteNavigation.FAVORITES_ACTIVITY.route
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onClick(menuItem.route)
        }
    ) {
        Box(
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                painter = painterResource(id = menuItem.iconRes),
                contentDescription = stringResource(id = menuItem.titleRes)
            )
            if (isCount) {
                Icon(
                    modifier = Modifier.size(13.dp),
                    tint = Color.Red,
                    painter = painterResource(id = R.drawable.elements),
                    contentDescription = ""
                )
                TabText(
                    modifier = Modifier.padding(end = 3.dp),
                    color = colorResource(id = R.color.white),
                    text = "$sizeFavorites",
                )
            }

        }
        TabText(
            text = stringResource(id = menuItem.titleRes)
        )


    }

}