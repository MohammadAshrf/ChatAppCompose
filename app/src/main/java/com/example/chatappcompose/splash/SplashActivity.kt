package com.example.chatappcompose.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.chatappcompose.R
import com.example.chatappcompose.register.RegisterActivity
import com.example.chatappcompose.ui.theme.ChatAppComposeTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppComposeTheme {
                SplashContent()
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 1200)

            }
        }
    }

    @Composable
    fun SplashContent() {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (logo, signature) = createRefs()
            Image(painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Image",
                Modifier
                    .constrainAs(logo) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxSize(0.35F)
            )
            Image(painter = painterResource(id = R.drawable.signature),
                contentDescription = "App Signature",
                Modifier
                    .constrainAs(signature) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(0.6F)
                    .fillMaxHeight(0.15F)
            )

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        ChatAppComposeTheme {
            SplashContent()
        }
    }
}