package com.example.jobsearchproject.view.favoritesScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jobsearchproject.R
import com.example.jobsearchproject.view.mainScreen.JobCard
import com.example.jobsearchproject.retrofit.Vacancy
import com.example.jobsearchproject.style.Text1
import com.example.jobsearchproject.style.Title2

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 1L
)
fun PreviewFavoritesScreen() {
    //FavoritesScreen()
}

@Composable
fun FavoritesScreen(
    favoritesVM: FavoritesVM = viewModel(factory = FavoritesVM.factory),
    getTextNumberVacancy: (Int) -> String,
    getTextData: (String) -> String,
    onClickFavorites: (Vacancy) -> Unit,
    viewVacancy: (Vacancy) -> Unit,
    navigation: () -> Unit,
    getTextLookingNumber: (Int) -> String
) {
    val listFavorites by favoritesVM.listFavorites.collectAsState(initial = emptyList())
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Title2(
            text = "Избранное"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text1(
            color = colorResource(id = R.color.basic_grey3),
            text = getTextNumberVacancy(listFavorites.size)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(

        ) {
            items(listFavorites) { vacancy ->
                JobCard(
                    itemVacancy = vacancy,
                    dataText = getTextData(vacancy.publishedDate),
                    viewVacancy = {
                        viewVacancy(it)
                    },
                    navigation = {
                        navigation()
                    },
                    onClickFavorites = {
                        onClickFavorites(vacancy)
                    },
                    text = {
                        if (vacancy.lookingNumber != null) {
                            getTextLookingNumber(vacancy.lookingNumber)
                        } else ""
                    }
                )
                //getTextLookingNumber()

            }
        }
    }
}