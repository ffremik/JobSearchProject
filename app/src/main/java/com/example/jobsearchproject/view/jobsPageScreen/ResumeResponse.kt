package com.example.jobsearchproject.view.jobsPageScreen

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jobsearchproject.R
import com.example.jobsearchproject.view.loginOneScreen.ButtonItem
import com.example.jobsearchproject.style.ButtonText1
import com.example.jobsearchproject.style.Text1
import com.example.jobsearchproject.style.Title3

@Composable
@Preview(showBackground = true)
fun PreviewResumeResponse(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
       // ResumeResponse(true){}
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumeResponse(
    vacancy: String,
    isOpenAddContent: Boolean,
    updateIsOpenAddContent: () -> Unit,
    onDismissClick: () -> Unit
){
    var userContent by remember { mutableStateOf("") }
        AlertDialog(
            modifier = Modifier
                .background(color = colorResource(id = R.color.basic_black)),
            onDismissRequest = {
                onDismissClick()
            }
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
               // modifier = Modifier.background(color = colorResource(id = R.color.basic_black))
            ) {
                Row(

                ) {
                    Image(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(18.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text1(
                            color = colorResource(id = R.color.basic_grey3),
                            text = "Резюме для отклика"
                        )
                        Title3(
                            text = vacancy
                        )
                    }

                }
                Spacer(modifier = Modifier.height(64.dp))
                if (isOpenAddContent){
                    OutlinedTextField(
                        label = {
                            Text(
                                text = "Ваше сопроводительное письмо"
                            )
                        },
                        value = userContent,
                        onValueChange = {
                            userContent = it
                        }
                    )
                } else {
                    ButtonText1(
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .clickable {
                                updateIsOpenAddContent()
                            }
                            .fillMaxWidth(),
                        color = colorResource(id = R.color.special_green),
                        text = "Добавить сопроводительное"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                ButtonItem(
                    colorButton = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.special_green)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        onDismissClick()
                    }
                ) {
                    ButtonText1(
                        color = colorResource(id = R.color.white),
                        text = stringResource(id = R.string.reply)
                    )
                }
                Spacer(modifier = Modifier.height(64.dp))
            }
        }

}