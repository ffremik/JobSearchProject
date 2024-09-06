package com.example.jobsearchproject.view.mainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jobsearchproject.R
import com.example.jobsearchproject.view.loginOneScreen.ButtonItem
import com.example.jobsearchproject.view.loginOneScreen.EditItem
import com.example.jobsearchproject.view.mainScreen.viewmodel.MainVM
import com.example.jobsearchproject.style.ButtonText1
import com.example.jobsearchproject.style.Text1
import com.example.jobsearchproject.style.Title2

@Composable
@Preview(showBackground = true)
fun PreviewMainScreen() {
    MainScreen(mainVM = viewModel()){}
}

@Composable
fun MainScreen(
    mainVM: MainVM,
    navigationJobsPage: () -> Unit
) {
    val isShowAllItems by mainVM.isShowAllItems.collectAsState()
    val list by mainVM.vacancies.collectAsState()
    
    Column(
        modifier = Modifier
            .padding(16.dp)

    ) {
        val listAll by mainVM.allListFavorites.collectAsState(initial = emptyList())


        SearchItem(
            isShowAllItems,
            sizeVacancy = "${mainVM.getVacanciesText(list.size)}"
        ) {
            mainVM.updateIsShowAllItems()
        }

        if (mainVM.offers.isNotEmpty() && !isShowAllItems) {
            LazyRow(
                modifier = Modifier
            ) {
                items(mainVM.offers) {
                    RecommendationItem(offerItem = it)
                }
            }
        }
        LazyColumn {
            item {
                Title2(
                    text = "Вакансии для вас"
                )
            }
            items(if (isShowAllItems) list else list.take(3)) { vacancy ->
                JobCard(
                    vacancy,
                    dataText = mainVM.formatDate(vacancy.publishedDate),
                    navigation = { navigationJobsPage() },
                    viewVacancy = {
                        mainVM.viewVacancy(it)
                    },
                    onClickFavorites = {
                        mainVM.updateIsFavorites(vacancy)
                    }
                ) {
                    mainVM.getLookingText(it)
                }
            }
            if (!isShowAllItems) {
                item {
                    ButtonItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = ShapeDefaults.Small,
                        colorButton = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.special_blue)
                        ),
                        onClick = { mainVM.updateIsShowAllItems() }
                    ) {
                        ButtonText1(
                            color = colorResource(id = R.color.white),
                            text = "Еще ${mainVM.getVacanciesText(list.size - 3)}"
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun SearchItem(
    isShowAllItems: Boolean,
    sizeVacancy: String,
    onClick: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                EditItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    inputText = text,
                    onClick = { /*TODO*/ },
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.clickable {
                                if (isShowAllItems) {
                                    onClick()
                                }
                            },
                            painter = if (isShowAllItems) painterResource(id = R.drawable.icon_left) else painterResource(
                                id = R.drawable.icon_search
                            ),
                            contentDescription = "search"
                        )
                    },
                    updateText = {
                        text = it
                    }
                ) {
                    Text(
                        text = "Должность, ключевые слова",
                        fontSize = 10.sp
                    )
                }
                Text(
                    modifier = Modifier.padding(24.dp),
                    text = text
                )
            }

        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .size(40.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .size(24.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_filter),
                    contentDescription = "filter"
                )
            }
        }

    }

    if (isShowAllItems) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text1(text = sizeVacancy)
            Row(
                verticalAlignment = Alignment.CenterVertically,
               // modifier = Modifier.fillMaxHeight()
            ) {
                Text1(
                    color = colorResource(id = R.color.special_blue),
                    text = "По соответствию"
                )
                Icon(
                    painter = painterResource(id = R.drawable.icon_sorted),
                    contentDescription = "sorted" )
            }

        }
        Spacer(modifier = Modifier.height(24.dp))
    }

}