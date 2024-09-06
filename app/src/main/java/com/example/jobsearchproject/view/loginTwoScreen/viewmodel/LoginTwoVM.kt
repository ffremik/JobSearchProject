package com.example.jobsearchproject.view.loginTwoScreen.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.example.jobsearchproject.R
import kotlinx.coroutines.flow.MutableStateFlow

class LoginTwoVM : ViewModel(){
    val isValid = MutableStateFlow(false)
    val colorTextButton = MutableStateFlow(R.color.basic_grey)


    val inputCodeUser = mutableStateListOf<String>("", "", "", "")

    fun addUserCode(index: Int, userInput: String) {
        inputCodeUser[index] = userInput
    }

    fun isValidUserCode() {
        var isVal = true
        for (code in inputCodeUser){
            if (code.isEmpty()) {
                isVal = false
            }
        }
        if (isVal) {
            isValid.value = true
            colorTextButton.value = R.color.white
        } else {
            isValid.value = false
            colorTextButton.value = R.color.basic_grey
        }
//        isValid.value = inputCodeUser.isEmpty()
//        isValid.value =

//        for (code in inputCodeUser) {
//            if (!code.all { it.isDigit() }){
//                return false
//            }
//        }
//        return true
    }
}