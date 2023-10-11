package com.example.chatappcompose.base

import androidx.compose.runtime.mutableStateOf

class BaseViewModel {
    val dialogMessageState = mutableStateOf("")
    val isLoadingState = mutableStateOf(false)
}