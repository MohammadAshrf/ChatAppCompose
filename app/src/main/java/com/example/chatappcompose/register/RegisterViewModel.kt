package com.example.chatappcompose.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.chatappcompose.firestore.UsersDao
import com.example.chatappcompose.firestore.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {
    val userNameState = mutableStateOf("")
    val userNameErrorState = mutableStateOf("")
    val emailState = mutableStateOf("")
    val emailErrorState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val passwordErrorState = mutableStateOf("")
    val passwordConfirmationState = mutableStateOf("")
    val passwordConfirmationErrorState = mutableStateOf("")
    val dialogMessageState = mutableStateOf("")
    val isLoadingState = mutableStateOf(false)
    val navigator: Navigator? = null

    private val auth = Firebase.auth

    fun register() {
        if (!validateFields()) return
        //loading
        isLoadingState.value = true
        auth.createUserWithEmailAndPassword(
            emailState.value,
            passwordState.value
        )
            .addOnCompleteListener { task ->
                isLoadingState.value = false
                if (task.isSuccessful) {
                    insertUserToFirestore(task.result.user?.uid)


                } else {
                    //show error
                    dialogMessageState.value =
                        task.exception?.localizedMessage ?: "Something went wrong"
                }
            }
    }

    private fun insertUserToFirestore(uid: String?) {
        val user = User(
            id = uid,
            userName = userNameState.value,
            email = emailState.value
        )
        UsersDao.createUser(user) { task ->
            if (task.isSuccessful) {

            } else {
                dialogMessageState.value =
                    task.exception?.localizedMessage ?: "Something went wrong"
            }
        }
    }


    private fun validateFields(): Boolean {
        var isValid = true
        if (userNameState.value.isBlank()) {
            userNameErrorState.value = "First name required"
            isValid = false
        } else
            userNameErrorState.value = ""

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

}



