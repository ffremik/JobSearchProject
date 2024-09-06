package com.example.jobsearchproject.style

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.jobsearchproject.R

@Composable
@Preview(showBackground = true)
fun PreviewStyleText(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Title1(text = "Проверка")
        Title2(text = "Проверка")
        Title3(text = "Проверка")
        Title4(text = "Проверка")
        Text1(text = "Проверка")
        ButtonText1(text = "Проверка")
        ButtonText2(text = "Проверка")
        TabText(text = "Проверка")
        Number(text = "Проверка")

    }
}

@Composable
fun Title1(
    text: String,
    color: Color = Color.White
) {
    Text(
        text = text,
        fontSize = 22.sp,
        color = color
    )
}

@Composable
fun Title2(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 20.sp,
        color = Color.White
    )
}

@Composable
fun Title3(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        color = color
    )
}

@Composable
fun Title4(
    text: String,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = Color.White
) {
    Text(
        maxLines = maxLines,
        text = text,
        fontSize = 14.sp,
        color = color
    )
}

@Composable
fun Text1(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 14.sp,
        color = color
    )
}

@Composable
fun ButtonText1(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    color: Color = colorResource(id = R.color.basic_grey)
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun ButtonText2(
    text: String,
    color: Color = colorResource(id = R.color.basic_grey)
) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = color
    )
}

@Composable
fun TabText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = colorResource(id = R.color.basic_grey)
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 10.sp,
        color = color
    )
}

@Composable
fun Number(text: String) {
    Text(
        text = text,
        fontSize = 7.sp,
        color = colorResource(id = R.color.basic_grey)
    )
}