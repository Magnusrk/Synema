package com.example.synema.view.screens

import GradientBox
import MoviePosterFrame
import android.util.Log
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.Data.users.UserAPISource
import com.example.synema.model.ApiResponse
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.UserModel
import com.example.synema.view.components.SynemaLogo


@Composable
fun LoginScreen(navController : NavHostController, profileState: MutableState<ProfileModel>) {
    GradientBox(){
        ContentContainer(navController, profileState);
    }
}

@Composable
private fun ContentContainer(navController: NavController, profileState : MutableState<ProfileModel>){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
    ){
        SynHeader()
        MovieDisplay();
        UserLoginArea(navController, profileState);
    }
}


@Composable
private fun MovieDisplay(){
    val dataSource = DependencyProvider.getInstance().getMovieSource();

    var newList : List<MovieModel> by remember {
        mutableStateOf(listOf())
    }

    dataSource.loadDiscoverMovies (){
        it.getResult()?.let {movieModel ->
            newList = movieModel
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)

    ){
        if (newList.size >= 3) {
            MoviePosterFrame(
                Arrangement.Bottom,
                newList.get(0).poster_url,
                offsetX = 15.dp,
                zindex = 0f
            )
            MoviePosterFrame(
                Arrangement.Center,
                newList.get(1).poster_url,
                zindex = 1f
            )
            MoviePosterFrame(
                Arrangement.Top,
                newList.get(2).poster_url,
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
private fun UserLoginArea(navController: NavController, profileState : MutableState<ProfileModel>){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    val error = remember {
        mutableStateOf(""
        )
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        LoginInputField(
            label="Email",
            onChange = { email = it},
        );
        LoginInputField(
            label="Password",
            isHidden=true,
            onChange = { password = it},
            onDone = {sendLoginRequest(email, password, navController, profileState, error)}

        );
        Column (horizontalAlignment = Alignment.CenterHorizontally
        ){
            OpaqueButton(label = "Log In", onClick = { sendLoginRequest(email, password, navController, profileState, error)});
            OpaqueButton(label = "Get started now", onClick = {navController.navigate("signup");});
            Text(error.value, color=Color.Red)
        }

    }


}

private fun sendLoginRequest(
    email: String,
    password: String,
    navController: NavController,
    profileState: MutableState<ProfileModel>,
    error: MutableState<String>
) {

    val source = DependencyProvider.getInstance().getUserSource();
    source.LoginUser(email, password, callback = {

        Log.d("Main", it.getStatus())
        Log.d("Main", it.successful().toString())
        if(it.successful()){
            profileState.value = it.getResult()?.profile!!;
            if(profileState.value.token == null){
                profileState.value.token = "none"
            }

            navController.navigate("home")
        } else{
            error.value = (it.getStatus())
        }
    });
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
