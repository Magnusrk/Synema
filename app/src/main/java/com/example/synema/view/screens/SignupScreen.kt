package com.example.synema.view.screens

import GradientBox
import com.example.synema.view.components.OpaqueButton
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.synema.view.components.SynemaLogo
import com.example.synema.viewmodel.Landing.SignupViewModel


@Composable
fun SignupScreen() {
    val signupViewModel : SignupViewModel = viewModel();
    GradientBox(){
        ContentContainer(signupViewModel);
    }
}

@Composable
private fun ContentContainer(signupViewModel: SignupViewModel){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
    ){
        SynHeader()
        UserSignUpArea(signupViewModel);
    }
}


@Composable
private fun SynHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 50.dp)

    ) {
        SynemaLogo()
    }
}

@Composable
private fun UserSignUpArea(signupViewModel: SignupViewModel){
    val c  = LocalContext.current;
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        LoginInputField(
            label="Username",
            isHidden= false,
            onChange = { signupViewModel.editUsername(it)},
            onDone = {}
        );
        LoginInputField(
            label="Email",
            isHidden= false,
            onChange = { signupViewModel.editEmail(it)},
            onDone = {}
        );
        LoginInputField(
            label="Password",
            isHidden=true,
            onChange = { signupViewModel.editPassword(it)},
            onDone = {signupViewModel.signup(c) }

        );
        Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top=50.dp)
        ){
            OpaqueButton(label = "Sign up", onClick = {signupViewModel.signup(c)});
            Box(modifier= Modifier.height(50.dp))
            Text("By signing up you agree on our", color=Color.White, fontSize = 10.sp)
            OpaqueButton(label = "Terms of Service", onClick = {});
            OpaqueButton(label = "Login instead", onClick = {signupViewModel.login()});
            Text(signupViewModel.error.value, color=Color.Red)
        }

    }


}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginInputField(label : String, isHidden : Boolean, onChange : (String) -> Unit, onDone : () -> Unit){
    var text by remember { mutableStateOf("") }

    if(!isHidden){

        val containerColor = Color(0,0,0,0)
        //textColor = Color.White,
        TextField(
            value = text,
            onValueChange = { text = it ; onChange(text)},
            label = { Text(label) },
            modifier = Modifier.padding(7.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                focusedIndicatorColor = Color(0xFF811C77),
                unfocusedIndicatorColor = Color(0xFFC5AC29),
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                unfocusedTextColor = Color(0xFFBF76FF),
                focusedTextColor = Color.White
            )
        )
    } else{
        val containerColor = Color(0,0,0,0)
        //textColor = Color.White,
        TextField(
            value = text,
            onValueChange = { text = it ; onChange(text)},
            label = { Text(label) },
            maxLines = 1,
            singleLine = true,
            modifier = Modifier.padding(7.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(
                onDone = { onDone()}),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                focusedIndicatorColor = Color(0xFF811C77),
                unfocusedIndicatorColor = Color(0xFFC5AC29),
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                unfocusedTextColor = Color(0xFFBF76FF),
                focusedTextColor = Color.White
            )
        )
    }
}
