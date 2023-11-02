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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.synema.controller.UserAPI
import com.example.synema.model.ProfileModel
import com.example.synema.model.UserModel
import com.example.synema.view.components.SynemaLogo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
     fun SignupScreen(navController : NavHostController, profileState: MutableState<ProfileModel>) {
        GradientBox(){
            ContentContainer(navController, profileState);
        }
    }

    @Composable
    private fun ContentContainer(navController: NavController, profileState: MutableState<ProfileModel>){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
            ){
            SynHeader()
            UserSignUpArea(navController, profileState);
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
private fun UserSignUpArea(navController: NavController, profileState: MutableState<ProfileModel>){
    var username by remember { mutableStateOf("") }
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
            label="Username",
            isHidden= false,
            onChange = { username = it},
            onDone = {}
        );
        LoginInputField(
            label="Email",
            isHidden= false,
            onChange = { email = it},
            onDone = {}
        );
        LoginInputField(
            label="Password",
            isHidden=true,
            onChange = { password = it},
            onDone = {sendSignUpRequest(
                username,
                email,
                password,
                navController,
                profileState,
                error
                ); }

        );
        Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top=50.dp)
        ){
            OpaqueButton(label = "Sign up", onClick = {sendSignUpRequest(
                username,
                email,
                password,
                navController,
                profileState,
                error
            );});
            Box(modifier= Modifier.height(50.dp))
            Text("By signing up you agree on our", color=Color.White, fontSize = 10.sp)
            OpaqueButton(label = "Terms of Service", onClick = {navController.navigate("home");});
            OpaqueButton(label = "Login instead", onClick = {navController.navigate("login");});
            Text(error.value, color=Color.Red)
        }

    }


}

fun sendSignUpRequest(
    username: String,
    email: String,
    password: String,
    navController: NavController,
    profileState: MutableState<ProfileModel>,
    error: MutableState<String>
) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://0.0.0.0:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(UserAPI::class.java)

    val call: Call<UserModel?>? = api.userSignup(username, email,password);

    call!!.enqueue(object: Callback<UserModel?> {
        override fun onResponse(call: Call<UserModel?>, response: Response<UserModel?>) {
            if(response.isSuccessful) {
                //Sign up successful
                //Set profile state and navigate
                navController.navigate("home");
                //profileState.value = response.body()!!.profile

            }
        }

        override fun onFailure(call: Call<UserModel?>, t: Throwable) {
            //Sign up failed
            if(email == "" || username == "" || password == ""){
                error.value = "Some fields are left empty"
            } else{

                navController.navigate("home");
                profileState.value = ProfileModel("0", "Chuck", email,"")
            }

        }
    })
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginInputField(label : String, isHidden : Boolean, onChange : (String) -> Unit, onDone : () -> Unit){
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

