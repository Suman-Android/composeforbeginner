package com.example.composeforbeginner

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeforbeginner.ui.theme.ComposeForBeginnerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeForBeginnerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Login()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login() {
    val userId = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val showPassword = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            modifier = Modifier.size(200.dp).align(Alignment.TopCenter),
            painter = painterResource(id = R.drawable.compose),
            contentDescription = "android",
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            OutlinedTextField(
                value = userId.value,
                onValueChange = { userId.value = it },
                label = { Text(text = "User Id") },
                placeholder = { Text(text = "Enter your user id") },
                maxLines = 1
            )

            OutlinedTextField(
                value = password.value, onValueChange = { password.value = it },
                label = { Text(text = "Password") }, modifier = Modifier.padding(top = 10.dp),
                maxLines = 1,
                placeholder = { Text(text = "Enter your password") },
                visualTransformation = if (!showPassword.value) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val passwordImage = if (showPassword.value) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }
                    IconButton(onClick = { showPassword.value = !showPassword.value }) {
                        Icon(imageVector = passwordImage, contentDescription = "password")
                    }
                }
            )

            FilledTonalButton(modifier = Modifier.padding(top = 20.dp), onClick = {
                Toast.makeText(context, "Login Button Clicked.", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Login")
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun LoginPreview() {
    ComposeForBeginnerTheme {
        Login()
    }
}