package com.example.chatappcompose.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.isTraceInProgress
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatappcompose.R
import com.example.ui.theme.ChatAppComposeTheme


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
fun RegisterContent(vm: RegisterViewModel = viewModel()) {
    Scaffold {
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
                state = vm.firstNameState,
                "First Name",
                vm.firstNameErrorState
            )
            ChatTextField(
                state = vm.emailState,
                "Email",
                vm.emailErrorState
            )
            ChatTextField(
                state = vm.passwordState,
                "Password",
                vm.passwordErrorState,
                isPassword = true
            )

            ChatTextField(
                state = vm.passwordConfirmationState,
                "Password Confirmation",
                vm.passwordConfirmationErrorState,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = ("Already have account?"), style = TextStyle(
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.grey)
                ), modifier = Modifier.padding(start = 18.dp, top = 24.dp)
            )

            ChatButton(label = "Create Account",
                onClick = { vm.sendDataToFirebaseAuth() })
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