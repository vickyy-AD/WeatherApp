package com.vicki.weatherappliation.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.vicki.weatherappliation.SimpleToolbar
import com.vicki.weatherappliation.api.AppConstants
import com.vicki.weatherappliation.api.NetworkResponse
import com.vicki.weatherappliation.vm.AuthVM

@Composable
fun RegisterScreen(navController: NavHostController, current: Context) {
    val loginViewModel: AuthVM = viewModel()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val userName = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }

    val registerResponse = loginViewModel.registerLiveResponse.observeAsState()

    when (val result = registerResponse.value) {
        is NetworkResponse.Error -> {
            Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
            isLoading.value = false
        }

        NetworkResponse.Loading -> {}
        is NetworkResponse.Success -> {
            Toast.makeText(context, result.data.message, Toast.LENGTH_SHORT).show()
            isLoading.value = false
        }

        null -> {}
    }

    Surface {
        SimpleToolbar(headerText = "Register Screen", onNavigationClick = {
            navController.popBackStack()
         })

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp), contentAlignment = Alignment.Center
        ) {
            Column {
                RegisterColumns(
                    navController,
                    loginViewModel,
                    userName,
                    phone,
                    password,
                    keyboardController,
                    isLoading,
                    context
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterColumns(
    navController: NavHostController,
    loginViewModel: AuthVM,
    userName: MutableState<String>,
    phone: MutableState<String>,
    password: MutableState<String>,
    keyboardController: SoftwareKeyboardController?,
    isLoading: MutableState<Boolean>,
    context: Context
) {

    OutlinedTextField(
        value = userName.value,
        onValueChange = { newText ->
            if (newText.length <= 10) {
                userName.value = newText
            }
        },
        label = { Text("Enter User Name") },
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        textStyle = LocalTextStyle.current.copy(color = Color.Black),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {

            }
        ),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Blue, // Customize border color when focused
            unfocusedBorderColor = Color.Gray, // Customize border color when unfocused
            // Customize text color
            cursorColor = Blue // Customize cursor color
        )
    )


    OutlinedTextField(
        value = phone.value,
        onValueChange = { newText ->
            if (newText.length <= 10) {
                phone.value = newText
            }
        },
        label = { Text("Enter Phone Number") },
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        textStyle = LocalTextStyle.current.copy(color = Color.Black),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {

            }
        ),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Blue, // Customize border color when focused
            unfocusedBorderColor = Color.Gray, // Customize border color when unfocused
            // Customize text color
            cursorColor = Blue // Customize cursor color
        )
    )



    OutlinedTextField(
        value = password.value,
        onValueChange = { newPassword ->
            password.value = newPassword
        },
        label = { Text("Enter Password") },
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        textStyle = LocalTextStyle.current.copy(color = Color.Black),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue, // Customize border color when focused
            unfocusedBorderColor = Color.Gray, // Customize border color when unfocused
            cursorColor = Color.Blue // Customize cursor color
        ),
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {

                navController.popBackStack()

            }, // Add padding if needed
        horizontalArrangement = Arrangement.End,

        ) {
        Text(
            text = "Already a Member! Login",
            color = Color.Black
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        if (!isLoading.value) {
            Button(
                onClick = {
                    if (phone.value.length == 10 && password.value.length >= 6) {
                        isLoading.value = true
                        keyboardController?.hide()

                        loginViewModel.callRegister(userName.value,phone.value, password.value)

                    }

                },
                modifier = Modifier
                    .padding(8.dp)
                    .height(56.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
            ) {
                Text("Register")
            }
        }

        if (isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(36.dp)
                    .padding(8.dp)
            )
        }
    }
}








