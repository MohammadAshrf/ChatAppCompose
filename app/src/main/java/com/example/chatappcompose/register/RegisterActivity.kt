package com.example.chatappcompose.register

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatappcompose.R
import com.example.chatappcompose.ui.theme.ChatAppComposeTheme


class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppComposeTheme {
                RegisterContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(viewModel: RegisterViewModel = viewModel()) {
    Scaffold(
        topBar = {
            Text(
                text = stringResource(id = R.string.register),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp, fontFamily = FontFamily.Monospace
                )
            )
        },
        contentColor = colorResource(
            id = R.color.white
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.background),
                    contentScale = ContentScale.FillBounds,
                )
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.34F))
            ChatTextField(
                state = viewModel.userNameState,
                "First Name",
                viewModel.userNameErrorState
            )
            ChatTextField(
                state = viewModel.emailState,
                "Email",
                viewModel.emailErrorState
            )
            ChatTextField(
                state = viewModel.passwordState,
                "Password",
                viewModel.passwordErrorState,
                isPassword = true
            )

            ChatTextField(
                state = viewModel.passwordConfirmationState,
                "Password Confirmation",
                viewModel.passwordConfirmationErrorState,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = ("Already have account?"), style = TextStyle(
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.grey)
                ), modifier = Modifier.padding(start = 18.dp, top = 24.dp)
            )

            ChatButton(
                label = "Create Account",
                onClick = {
                    viewModel.register()
                })
        }
        LoadingDialog()
        ChatDialog()
    }
}


@Composable
fun ChatDialog(viewModel: RegisterViewModel = viewModel()) {
    if (viewModel.dialogMessageState.value.isNotEmpty())
        AlertDialog(onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = {
                    viewModel.dialogMessageState.value = ""
                }) {
                    Text(
                        text = "OK",
                        style = TextStyle(color = colorResource(id = R.color.blue))
                    )
                }
            }, text = {
                Text(
                    text = viewModel.dialogMessageState.value,
                    style = TextStyle(fontSize = 16.sp)
                )
            })
}

@Composable
fun LoadingDialog(viewModel: RegisterViewModel = viewModel()) {
    if (viewModel.isLoadingState.value)
        Dialog(onDismissRequest = {}) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = colorResource(id = (R.color.blue)),
                    modifier = Modifier
                        .width(35.dp)
                        .height(35.dp)
                )
            }
        }
}


@Composable
fun ChatButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .shadow(
                elevation = 2.dp,
                spotColor = colorResource(id = R.color.grey2)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.white),
            contentColor = colorResource(id = R.color.silver)
        )
    ) {
        Text(
            text = label,
            fontSize = 14.sp
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.8F)
                .fillMaxHeight(0.35F)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "Icon Arrow Right"
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTextField(
    state: MutableState<String>,
    label: String,
    errorState: MutableState<String>,
    isPassword: Boolean = false
) {
    TextField(
        value = state.value,
        onValueChange = { newValue ->
            state.value = newValue
        },
        label = {
            Text(
                text = label, style = TextStyle(
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.grey),
                ), maxLines = 1
            )
        },
        isError = errorState.value.isNotEmpty(),
        visualTransformation = if (isPassword)
            PasswordVisualTransformation() else VisualTransformation.None,

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .background(Color.Transparent),


        colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
    )
    if (errorState.value.isNotEmpty())
        Text(
            text = errorState.value,
            style = TextStyle(color = Color.Red),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp),
            maxLines = 1
        )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ChatAppComposeTheme {
        RegisterContent()
    }
}