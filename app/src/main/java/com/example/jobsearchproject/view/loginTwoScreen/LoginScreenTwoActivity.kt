package com.example.jobsearchproject.view.loginTwoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jobsearchproject.R
import com.example.jobsearchproject.view.loginOneScreen.ButtonItem
import com.example.jobsearchproject.view.loginTwoScreen.viewmodel.LoginTwoVM
import com.example.jobsearchproject.style.ButtonText1
import com.example.jobsearchproject.style.Title2
import com.example.jobsearchproject.style.Title3

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 1
)
fun PreviewLoginTwoScreen() {
    LoginScreenTwoActivity(emailUser = "ffrem811@gmail.com") {}
}

@Composable
fun LoginScreenTwoActivity(
    loginTwoVM: LoginTwoVM = viewModel(),
    emailUser: String,
    onNavigation: () -> Unit
) {
    val colorTextAction by loginTwoVM.colorTextButton.collectAsState()
    val isValidCode by loginTwoVM.isValid.collectAsState()
    val focusManager = LocalFocusManager.current


    Column(
       verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp)
    ) {
        Spacer(modifier = Modifier.height(130.dp))
        Title2(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.text_code_sent_to) + " $emailUser"
        )
        Title3(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            text = stringResource(id = R.string.login_security_prompt)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            loginTwoVM.inputCodeUser.forEachIndexed { index, s ->
                PinCodeField(
                    focusManager = focusManager,
                    userInput = s,
                    index = index,
                    isValidCode = {
                        loginTwoVM.isValidUserCode()
                    }
                ) { id, s ->
                    loginTwoVM.addUserCode(id, s)
                }
            }

        }
        ButtonItem(
            colorButton = ButtonDefaults.buttonColors(
                disabledContainerColor = colorResource(id = R.color.special_dark_blue),
                containerColor = colorResource(id = R.color.special_blue)
            ),
            shape = ShapeDefaults.Small,
            enable = isValidCode,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            onClick = { onNavigation() }
        ) {
            ButtonText1(
                color = colorResource(id = colorTextAction),
                text = stringResource(id = R.string.confirm)
            )
        }
    }

}


@Composable
fun PinCodeField(
    focusManager: FocusManager,
    userInput: String,
    index: Int,
    isValidCode: () -> Unit,
    onClick: (Int, String) -> Unit

) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .clip(ShapeDefaults.Small)
            .background(colorResource(id = R.color.basic_grey2)),
        contentAlignment = Alignment.Center
    ) {
        if (userInput.isNotEmpty()) {
            Text(
                textAlign = TextAlign.Center,
                text = userInput,
                fontSize = 24.sp
            )
        } else {
            Text(
                textAlign = TextAlign.Center,
                text = "*",
                fontSize = 24.sp
            )
        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.alpha(0f)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .size(48.dp)
                    .padding(4.dp)
                    .align(Alignment.Center),
                value = userInput,
                maxLines = 1,
                singleLine = true,
                colors = TextFieldDefaults.colors(

                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                onValueChange = {
                    if (userInput.length < 1 && index < 3 && it.isDigitsOnly()) {
                        onClick(index, it)
                        focusManager.moveFocus(FocusDirection.Next)
                    } else if (userInput.length < 1 && index >= 3 && it.isDigitsOnly()) {
                        onClick(index, it)
                        focusManager.clearFocus()
                    } else {
                        if (it.length <= 1 && it.isDigitsOnly()) {
                            onClick(index, it)
                        }

                    }
                    isValidCode()

                }
            )
        }
    }

}


