package com.vicki.weatherappliation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vicki.weatherappliation.api.AppConstants
import com.vicki.weatherappliation.api.NetworkResponse
import com.vicki.weatherappliation.vm.AuthVM
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(navController: NavHostController, loginViewModel: AuthVM) {

    Surface() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp), contentAlignment = Alignment.Center
        ) {
            Column {
                PhoneNo(navController,loginViewModel)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNo(navController: NavHostController, loginViewModel: AuthVM) {

    loginViewModel.loginResult

    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginResult = loginViewModel.loginResult.observeAsState()


    OutlinedTextField(
        value = phone,
        onValueChange = { newText ->
            if (newText.length <= 10) {
                phone = newText
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
        value = password,
        onValueChange = { newPassword ->
            password = newPassword
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
                // Handle action when Done button is pressed on the keyboard
                // For example, you can perform validation or submit the password
                // You can also request focus to another field if needed
            }
        ),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue, // Customize border color when focused
            unfocusedBorderColor = Color.Gray, // Customize border color when unfocused
            cursorColor = Color.Blue // Customize cursor color
        ),
    )


    var isLoading by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Button or progress indicator based on isLoading state

        if (!isLoading) {
            Button(
                onClick = {
                    if (phone.length == 10 && password.length >= 6) {
                        isLoading = true

                       loginViewModel.callLogin(phone,password)

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
                Text("Submit")
            }
        }

        if (isLoading) {
            LaunchedEffect(Unit) {
                delay(2000) // Simulate a delay (replace with actual async operation)
                isLoading = false // Reset loading state after delay

            }
            CircularProgressIndicator(
                modifier = Modifier
                    .size(36.dp)
                    .padding(8.dp)
            )
        }

        when(loginResult.value){
            is NetworkResponse.Error -> {

            }
            NetworkResponse.Loading -> {
             }
            is NetworkResponse.Success -> {
                navController.navigate(AppConstants.RouteConstants.HOME) {
                    popUpTo(AppConstants.RouteConstants.LOGIN) { inclusive = true }

                }
            }
            null ->{

            }
        }




    }


}








