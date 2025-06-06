package com.example.mobileapppro

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.Icon as Icon

/**
 * Composable function for the Sign Up screen.
 *
 * This screen allows users to create a new account by providing their username, email, and password.
 * It includes a "Remember Me" checkbox and a link to the login screen.
 *
 * @param navController The navigation controller used to navigate between screens.
 * @param viewModel The view model for the sign up screen.  Defaults to a new instance
 * created via `androidx.lifecycle.viewmodel.compose.viewModel()`.
 */

@Composable
fun SignUp(
    navController: NavController,
    viewModel: SignUpViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.wels),
            contentDescription = "welcome image",
            modifier = Modifier.fillMaxWidth().size(250.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Create An Account", fontSize = 28.sp)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.username,
                onValueChange = { viewModel.username = it },
                label = { Text("User Name") },
                shape = RoundedCornerShape(25.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.email,
                onValueChange = { viewModel.email = it },
                label = { Text("E-mail") },
                shape = RoundedCornerShape(25.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                label = { Text("Password") },
                visualTransformation = if (viewModel.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (viewModel.passwordVisible)
                        painterResource(id = R.drawable.baseline_visibility_24)
                    else
                        painterResource(id = R.drawable.baseline_visibility_off_24)

                    IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                        Icon(
                            painter = icon,
                            contentDescription = if (viewModel.passwordVisible) "Hide password" else "Show password",
                            tint = if (viewModel.passwordVisible) Color.Blue else Color.Gray
                        )
                    }
                },
                shape = RoundedCornerShape(25.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .border(2.dp, Color.Gray, RoundedCornerShape(4.dp))
                        .clickable { viewModel.toggleRememberMe() }
                ) {
                    if (viewModel.rememberMe) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Checked",
                            tint = Color.Red,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                Text(
                    "Remember Me",
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.signUp {
                        navController.navigate("home") {
                            popUpTo("signup") { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                },
                modifier = Modifier.padding(25.dp).width(150.dp).height(45.dp),
                shape = RoundedCornerShape(40.dp)
            ) {
                Text("Sign Up", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Signs {
                navController.navigate("login") {
                    popUpTo("signup") { inclusive = true }
                    launchSingleTop = true
                }
            }
        }
    }
}

/**
 * Composable function for displaying the "Already Signed Up? LogIn" text with a clickable "LogIn" part.
 *
 * This composable is used to provide a navigation option for users who already have an
 * account, allowing them to go directly to the login screen.
 *
 * @param controls Callback function to be executed when the "LogIn" text is clicked.  This
 * function is typically used to trigger navigation to the login screen.
 */
@Composable
fun Signs(controls:()->Unit){
    var newtext= buildAnnotatedString {
        append("Already Signed Up?")
        withStyle(style= SpanStyle(color = Color.Red)){
            append("LogIn")
        }
    }
TextButton(onClick = controls) {
    Text(text = newtext,
        fontSize = 18.sp,)
//        modifier = Modifier.clickable { })
}
}



