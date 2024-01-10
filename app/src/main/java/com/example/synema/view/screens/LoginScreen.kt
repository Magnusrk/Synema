package com.example.synema.view.screens

import GradientBox
import MoviePosterFrame
import com.example.synema.view.components.OpaqueButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.synema.view.components.SynemaLogo
import com.example.synema.viewmodel.Landing.LoginViewModel


@Composable
fun LoginScreen() {
    val loginViewModel : LoginViewModel = viewModel();
    loginViewModel.getMoviePosters()
    loginViewModel.checkUserLoggedIn(LocalContext.current)
    GradientBox(){
        ContentContainer(loginViewModel);
    }
}

@Composable
private fun ContentContainer(loginViewModel: LoginViewModel){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
    ){
        SynHeader()
        MovieDisplay(loginViewModel);
        UserLoginArea(loginViewModel);
    }
}


@Composable
private fun MovieDisplay(loginViewModel: LoginViewModel){





    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)

    ){
        if (loginViewModel.movieBanners.size >= 3) {
            MoviePosterFrame(
                Arrangement.Bottom,
                loginViewModel.movieBanners[0].poster_url,
                offsetX = 15.dp,
                zindex = 0f
            )
            MoviePosterFrame(
                Arrangement.Center,
                loginViewModel.movieBanners[1].poster_url,
                zindex = 1f
            )
            MoviePosterFrame(
                Arrangement.Top,
                loginViewModel.movieBanners[2].poster_url,
                offsetX = (-15).dp,
                zindex = 0f
            )
        }
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
private fun UserLoginArea(loginViewModel: LoginViewModel){
    val c  =LocalContext.current;
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        LoginInputField(
            label="Email",
            onChange = { loginViewModel.editEmail(it)},
        );
        LoginInputField(
            label="Password",
            isHidden=true,
            onChange = { loginViewModel.editPassword(it)},
            onDone = {loginViewModel.login(c)}

        );
        Column (horizontalAlignment = Alignment.CenterHorizontally
        ){

            OpaqueButton(label = "Log In", onClick = { loginViewModel.login(c)});
            OpaqueButton(label = "Get started now", onClick = {loginViewModel.signup()});
            Text(loginViewModel.error.value, color=Color.Red)
        }

    }


}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginInputField(label : String, isHidden : Boolean = false, onChange : (String) -> Unit, onDone : () -> Unit = {}){
    var text by remember { mutableStateOf("") }

    if(!isHidden){

        TextField(
            value = text,
            onValueChange = { text = it ; onChange(text)},
            label = { Text(label) },
            modifier = Modifier.padding(7.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0,0,0,0),
                textColor = Color.White,
                unfocusedLabelColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedIndicatorColor = Color(0xFFC5AC29),
                focusedIndicatorColor = Color(0xFF811C77),

                )
        )
    } else{
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
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0,0,0,0),
                textColor = Color.White,
                unfocusedLabelColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedIndicatorColor = Color(0xFFC5AC29),
                focusedIndicatorColor = Color(0xFF811C77),
            )
        )
    }
}
