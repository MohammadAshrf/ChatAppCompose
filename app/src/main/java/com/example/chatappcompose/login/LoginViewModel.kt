package com.example.chatappcompose.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val emailState = mutableStateOf("")
    val emailErrorState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val passwordErrorState = mutableStateOf("")

}