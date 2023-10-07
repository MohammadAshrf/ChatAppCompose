package com.example.chatappcompose.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    val firstNameState = mutableStateOf("")
    val firstNameErrorState = mutableStateOf("")
    val emailState = mutableStateOf("")
    val emailErrorState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val passwordErrorState = mutableStateOf("")
    val passwordConfirmationState = mutableStateOf("")
    val passwordConfirmationErrorState = mutableStateOf("")

    fun validateFields(): Boolean {
        var isValid = true
        if (firstNameState.value.isBlank()) {
            firstNameErrorState.value = "First name required"
             isValid = false
        } else
            firstNameErrorState.value = ""

        if (emailState.value.isBlank()) {
            emailErrorState.value = "Email required"
             isValid = false
        } else
            emailErrorState.value = ""

        if (passwordState.value.isBlank()) {
            passwordErrorState.value = "Password required"
             isValid = false
        } else
            passwordErrorState.value = ""

        if (passwordConfirmationState.value != passwordState.value) {
            passwordConfirmationErrorState.value = "Password doesn't match"
             isValid = false
        } else
            passwordConfirmationErrorState.value = ""
        return isValid
    }

//        if (firstNameState.value.isEmpty() && firstNameState.value.isBlank()
//            && emailState.value.isEmpty() && emailState.value.isBlank()
//            && passwordState.value.isEmpty() && passwordState.value.isBlank()
//        ) {
//            firstNameErrorState.value = "First Name Required"
//            emailErrorState.value = "Email Required"
//            passwordErrorState.value = "Password Required"
//        }
//        return true
//    }
//      if (firstNameState.value.isEmpty() || firstNameState.value.isBlank()) {
//            firstNameErrorState.value = "First name Required"
//            return false
//        }
//        if (firstNameState.value.isNotEmpty() || firstNameState.value.isNotBlank()
//            && emailState.value.isEmpty() || emailState.value.isBlank()
//            && passwordState.value.isEmpty() || passwordState.value.isBlank()
//        ) {
//            firstNameErrorState.value = ""
//            emailErrorState.value = "Email Required"
//            passwordErrorState.value = "Password Required"
//
//        }
//        if (emailState.value.isEmpty() || emailState.value.isBlank()) {
//            emailErrorState.value = "Email Required"
//            return false
//        }
//        if (emailState.value.isNotEmpty() || emailState.value.isNotBlank()
//            && firstNameState.value.isEmpty() || firstNameState.value.isBlank()
//            && passwordState.value.isEmpty() || passwordState.value.isBlank()
//        ) {
//            emailErrorState.value = ""
//            firstNameErrorState.value = "First name Required"
//            passwordErrorState.value = "Password Required"
//
//        }
//        if (passwordState.value.isEmpty() || passwordState.value.isBlank()) {
//            passwordErrorState.value = "Password Required"
//            return false
//        }
//        if (passwordState.value.isNotEmpty() || passwordState.value.isNotBlank()
//            && firstNameState.value.isEmpty() || firstNameState.value.isBlank()
//            && emailState.value.isEmpty() || emailState.value.isBlank()
//        ) {
//            passwordErrorState.value = ""
//            firstNameErrorState.value = "First name Required"
//            emailErrorState.value = "Email Required"
//        }


    fun sendDataToFirebaseAuth() {
        if (validateFields()) {
        }
    }
}

