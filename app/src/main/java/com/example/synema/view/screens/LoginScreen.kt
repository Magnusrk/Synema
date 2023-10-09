package com.example.synema.view.screens

import GradientBox
import MoviePosterFrame
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.synema.view.components.SynemaLogo


@Composable
     fun LoginScreen(navController : NavHostController) {
        GradientBox(){
            ContentContainer(navController);
        }
    }

    @Composable
    private fun ContentContainer(navController: NavController){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
            ){
            SynHeader()
            MovieDisplay();
            UserLoginArea(navController);
        }
    }


    @Composable
    private fun MovieDisplay(){
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)

        ){
            MoviePosterFrame(Arrangement.Bottom, "https://static.posters.cz/image/750/plakater/interstellar-ice-walk-i23290.jpg")
            MoviePosterFrame(Arrangement.Center, "https://i.etsystatic.com/10683147/r/il/d4a024/4900691314/il_1080xN.4900691314_fu21.jpg")
            MoviePosterFrame(Arrangement.Top, "https://www.hollywoodreporter.com/wp-content/uploads/2023/06/French-Film-Poster-Barbie-Warner-Bros..jpg?w=999")
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
private fun UserLoginArea(navController: NavController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        LoginInputField(
            label="Email",
            isHidden= false,
            onChange = { email = it}
        );
        LoginInputField(
            label="Password",
            isHidden=true,
            onChange = { password = it}
        );
        Column (horizontalAlignment = Alignment.CenterHorizontally
        ){
            OpaqueButton(label = "Log In", onClick = {navController.navigate("home");});
            OpaqueButton(label = "Get started now", onClick = {});
        }

    }


}

private fun handleLogin(email : String, password : String, navController: NavController){
    navController.navigate("home");
}
@Composable
private fun OpaqueButton(label : String, onClick: () -> Unit){
    Button(onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0,0,0,0)
        )
    ){
        Text(label, color=Color.White,
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginInputField(label : String, isHidden : Boolean, onChange : (String) -> Unit){
    var text by remember { mutableStateOf("") }

    if(!isHidden){

    TextField(
        value = text,
        onValueChange = { text = it ; onChange(text)},
        label = { Text(label) },
        modifier = Modifier.padding(7.dp),
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
            modifier = Modifier.padding(7.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

