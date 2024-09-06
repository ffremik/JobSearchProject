package com.example.jobsearchproject.view.loginOneScreen.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jobsearchproject.R
import kotlinx.coroutines.flow.MutableStateFlow


class LoginOneVM : ViewModel() {
    val inputUser = MutableStateFlow("")
    val colorActionText = MutableStateFlow(R.color.basic_grey)
    val isValidEmail = MutableStateFlow(false)


    fun updateInputTextUser(userText: String) {
        inputUser.value = userText
        if (inputUser.value.isNotEmpty()) {
            colorActionText.value = R.color.white

            isValidEmail.value = false
        } else {
            colorActionText.value = R.color.basic_grey
            isValidEmail.value = false
        }
    }

    fun isValidEmail(): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        isValidEmail.value = !emailRegex.matches(inputUser.value)
        return isValidEmail.value
    }

    fun clearTextField() {
        inputUser.value = ""
        isValidEmail.value = false
        colorActionText.value = R.color.basic_grey
    }
}