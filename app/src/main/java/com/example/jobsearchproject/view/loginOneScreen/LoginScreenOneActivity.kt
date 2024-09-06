package com.example.jobsearchproject.view.loginOneScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jobsearchproject.R
import com.example.jobsearchproject.view.loginOneScreen.viewmodel.LoginOneVM
import com.example.jobsearchproject.style.ButtonText2
import com.example.jobsearchproject.style.Text1
import com.example.jobsearchproject.style.Title2
import com.example.jobsearchproject.style.Title3

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 1L
)
fun PreviewLoginScreenOne() {
    LoginScreenOneActivity({})
}

@Composable
fun LoginScreenOneActivity(
    navigation: (String) -> Unit,
    modifier: Modifier = Modifier,
    loginScreenVM: LoginOneVM = viewModel()
) {
    val inputUserLogin by loginScreenVM.inputUser.collectAsState()
    val colorActionText by loginScreenVM.colorActionText.collectAsState()
    val isValidEmail by loginScreenVM.isValidEmail.collectAsState()

    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Title2(
            text = stringResource(id = R.string.login_to_personal_account),
            modifier = Modifier.padding(start = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            LoginPhysicalPerson(
                inputText = inputUserLogin,
                colorTextRes = colorActionText,
                isError = isValidEmail,
                onClickIcon = {
                    loginScreenVM.clearTextField()
                },
                onClickButton = {
                    loginScreenVM.isValidEmail()
                },
                navigation = {
                    navigation(inputUserLogin)
                },
                updateText = {
                    loginScreenVM.updateInputTextUser(it)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            LoginLegalEntity()
        }
    }

}

@Composable
fun LoginPhysicalPerson(
    inputText: String,
    colorTextRes: Int,
    isError: Boolean,

    onClickIcon: () -> Unit,
    onClickButton: () -> Boolean,
    navigation: () -> Unit,
    updateText: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.special_black))
            .padding(16.dp)
    ) {
        Title3(
            text = stringResource(id = R.string.search_job)
        )
        EditItem(
            inputText = inputText,
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onClickIcon()
            },
            updateText = {
                updateText(it)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_answers),
                    contentDescription = ""
                )
            },
            label = {
                ButtonText2(
                    text = stringResource(id = R.string.email_or_phone)
                )
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(0.6f)
            ) {
                ButtonItem(
                    modifier = Modifier.fillMaxWidth(),
                    colorButton = ButtonDefaults.buttonColors(
                        disabledContainerColor = colorResource(id = R.color.special_dark_blue),
                        containerColor = colorResource(id = R.color.special_blue)
                    ),
                    shape = ShapeDefaults.Small,
                    enable = inputText.isNotEmpty(),
                    onClick = {
                        if (!onClickButton()) {
                            navigation()
                        }

                    }
                ) {
                    ButtonText2(
                        text = stringResource(id = R.string.next),
                        color = colorResource(id = colorTextRes)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.4f)
            ) {
                ButtonText2(
                    text = stringResource(id = R.string.login_password),
                    color = colorResource(id = R.color.special_blue)
                )
            }
        }
    }
}

@Composable
fun ButtonItem(
    modifier: Modifier = Modifier,
    colorButton: ButtonColors = ButtonDefaults.buttonColors(),
    enable: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    onClick: () -> Unit,
    textButton: @Composable () -> Unit
) {
    Button(
        enabled = enable,
        colors = colorButton,
        modifier = modifier,
        shape = shape,
        onClick = {
            onClick()
        }
    ) {
        textButton()

    }
}

@Composable
fun EditItem(
    inputText: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    onClick: () -> Unit,
    updateText: (String) -> Unit,
    leadingIcon: @Composable () -> Unit,
    label: @Composable () -> Unit,
) {
    Column(

    ) {
        OutlinedTextField(
            leadingIcon = leadingIcon,
            isError = isError,
            singleLine = true,
            value = inputText,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = modifier,
            trailingIcon = {
                if (inputText.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            onClick()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear"
                        )
                    }

                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.blackLite),
                focusedContainerColor = colorResource(id = R.color.blackLite),
                errorCursorColor = Color.Red,
                errorIndicatorColor = Color.Red,
                errorTrailingIconColor = Color.White

            ),

            maxLines = 1,
            label = label,
            onValueChange = updateText
        )
        if (isError) {
            Text1(
                text = stringResource(id = R.string.email_error),
                color = Color.Red
            )
        }

    }

}

@Composable
fun LoginLegalEntity() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.special_black))
            .padding(16.dp)
    ) {
        Title3(
            text = stringResource(id = R.string.search_employees)
        )
        ButtonText2(
            text = stringResource(id = R.string.vacancy_and_resume_base),
            color = Color.White
        )
        ButtonItem(
            modifier = Modifier.fillMaxWidth(),
            colorButton = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.special_green)
            ),
            onClick = {

            }
        ) {
            ButtonText2(
                text = stringResource(id = R.string.l_job_search),
                color = Color.White
            )
        }
    }
}