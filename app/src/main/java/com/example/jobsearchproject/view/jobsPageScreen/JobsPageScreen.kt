package com.example.jobsearchproject.view.jobsPageScreen

import android.inputmethodservice.Keyboard.Row
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jobsearchproject.R
import com.example.jobsearchproject.view.loginOneScreen.ButtonItem
import com.example.jobsearchproject.view.mainScreen.viewmodel.MainVM
import com.example.jobsearchproject.retrofit.Vacancy
import com.example.jobsearchproject.style.ButtonText1
import com.example.jobsearchproject.style.ButtonText2
import com.example.jobsearchproject.style.Text1
import com.example.jobsearchproject.style.Title1
import com.example.jobsearchproject.style.Title2
import com.example.jobsearchproject.style.Title3
import com.example.jobsearchproject.style.Title4

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 1L
)
fun PreviewJobsPageScreen() {
    // JobsPageScreen()
}

@Composable
fun JobsPageScreen(
    mainVM: MainVM = viewModel(),
    baskStack: () -> Unit
) {
    val isOpenReply by mainVM.isOpenReply.collectAsState()
    val isOpenAddContent by mainVM.isOpenAddContent.collectAsState()

    val item by mainVM.vacancy.collectAsState()
    val rememberScroll = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(rememberScroll)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconsPanel(
            vacancy = item,
            updateFavorites = { mainVM.updateIsFavorites(it) }

        ) { baskStack() }
        TitleJobsPage(item)
        VacancyStats(item) { mainVM.getLookingText(it) }
        CompanyAddress(item)
        CompanyDescription(item)
        TaskComponent(item)
        AskEmployerQuestion(item) { mainVM.updateIsOpenReply() }

    }
    if (isOpenReply) {
        ResumeResponse(
            vacancy = "${item?.title}",
            isOpenAddContent,
            updateIsOpenAddContent = {
                mainVM.updateIsOpenAddContent()
            }
        ){mainVM.updateIsOpenReply()}
    }

}

@Composable
fun AskEmployerQuestion(
    vacancy: Vacancy?,
    onClickReply: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Title4(text = stringResource(id = R.string.ask_your_employer))
        Title4(
            color = colorResource(id = R.color.basic_grey3),
            text = stringResource(id = R.string.answer_with_result)
        )
        if (vacancy?.questions != null) {
            for (button in vacancy.questions) {
                ButtonItem(
                    shape = CircleShape,
                    colorButton = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.basic_grey2)
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Title4(
                        text = button
                    )
                }

            }
        }

        //Тут не забыть список

        ButtonItem(
            shape = ShapeDefaults.Small,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .height(48.dp),
            colorButton = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.special_green)
            ),
            onClick = {
                onClickReply()
            }
        ) {
            ButtonText1(
                text = stringResource(id = R.string.reply),
                color = Color.White
            )
        }
    }
}

@Composable
fun TaskComponent(vacancy: Vacancy?) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Title2(
            text = stringResource(id = R.string.your_task)
        )
        Text1(
            text = "${vacancy?.responsibilities}"
        )
    }
}

@Composable
fun CompanyDescription(vacancy: Vacancy?) {
    if (vacancy?.description != null)
        Text1(
            text = vacancy.description
        )

}

@Composable
fun CompanyAddress(vacancy: Vacancy?) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(color = colorResource(id = R.color.special_black))
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Title3(
                text = "${vacancy?.company}"
            )
            Icon(
                tint = colorResource(id = R.color.basic_grey3),
                painter = painterResource(id = R.drawable.icon_confirmed),
                contentDescription = "confirmed"
            )
        }
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .clip(ShapeDefaults.Small),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Карта"
        )
        Text1(
            text = "${vacancy?.address?.town}, ${vacancy?.address?.street}, ${vacancy?.address?.house}"
        )

    }
}

@Composable
fun VacancyStats(
    vacancy: Vacancy?,
    getFormatText: (Int) -> String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        if (vacancy?.appliedNumber != null) {
            Row(
                modifier = Modifier
                    .clip(shape = ShapeDefaults.Small)
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.special_dark_green))
                    .weight(0.5f)
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Column(
                    modifier = Modifier.weight(0.8f)
                ) {
                    Text1(
                        text = "${getFormatText(vacancy.appliedNumber)} уже откликнулись",
                        modifier = Modifier
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.2f)
                ) {
                    Image(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.icon_reply),
                        contentDescription = ""
                    )
                }

            }
        } else {
            Row(
                modifier = Modifier
                    // .clip(shape = ShapeDefaults.Small)
                    .fillMaxWidth()
                    // .background(color = colorResource(id = R.color.special_dark_green))
                    .weight(0.5f)
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            ) {

            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        if (vacancy?.lookingNumber != null) {
            Row(
                modifier = Modifier
                    .clip(shape = ShapeDefaults.Small)
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.special_dark_green))
                    .weight(0.5f)
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Column(
                    modifier = Modifier.weight(0.8f)
                ) {
                    Text1(
                        text = "${getFormatText(vacancy.lookingNumber)} сейчас смотрят",
                        modifier = Modifier
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.2f)
                ) {
                    Image(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.icon_eye),
                        contentDescription = ""
                    )
                }

            }
        } else {
            Row(
                modifier = Modifier
                    // .clip(shape = ShapeDefaults.Small)
                    .fillMaxWidth()
                    //.background(color = colorResource(id = R.color.special_dark_green))
                    .weight(0.5f)
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            ) {

            }
        }
    }
}


@Composable
fun IconsPanel(
    vacancy: Vacancy?,
    updateFavorites: (Vacancy) -> Unit,
    baskStack: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            modifier = Modifier.clickable {
                baskStack()
            },
            painter = painterResource(id = R.drawable.icon_left),
            contentDescription = ""
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.type_eye_state_active),
                contentDescription = ""
            )
            Icon(
                painter = painterResource(id = R.drawable.type_share_state_default),
                contentDescription = ""
            )
            if (vacancy != null) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            updateFavorites(vacancy)
                        },
                    painter = if (vacancy.isFavorite) painterResource(id = R.drawable.icon_favorites_active)
                    else painterResource(id = R.drawable.icon_favorites),
                    tint = if (vacancy.isFavorite) colorResource(id = R.color.special_blue) else LocalContentColor.current,
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
fun TitleJobsPage(vacancy: Vacancy?) {
    Column(
        modifier = Modifier.padding(top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Title1(
            text = "${vacancy?.title}"
        )
        Text1(
            text = "${vacancy?.salary?.full}"
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text1(
                text = stringResource(id = R.string.required_experience) + " ${vacancy?.experience?.text}"
            )
            Text1(
                text = "${vacancy?.schedules?.joinToString()}"
            )
        }

    }
}