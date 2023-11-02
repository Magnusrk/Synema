package com.example.synema.view.screens

import GradientBox
import MoviePosterFrame
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.R
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.OpaqueButton
import com.example.synema.view.components.SynemaLogo
import com.example.synema.view.components.TopBar
import com.example.synema.view.utils.Size


@Composable
fun EditProfile(navController : NavHostController, profileState: MutableState<ProfileModel>) {
    var bio by remember { mutableStateOf(profileState.value.bio) }
    GradientBox(){
        Column {
            MainContainer(hasBottomNav = true){
                TopBar("" , Alignment.CenterStart, 20.sp, backArrow = true, navController = navController)
                ChangePicture()
                EditBio()
                BioInputField(
                    label="Edit bio",
                    onChange = {bio = it},
                    onDone = {profileState.value.bio = bio},
                    startval = profileState.value.bio
                );
                ChangeEmail()
                ChangePassword()
            };
            BottomBar(navController = navController)
        }
    }
}

@Composable
private fun ChangePicture(){
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxWidth()){
        OpaqueButton("Change picture", onClick = {}, modifier= Modifier.defaultMinSize(minHeight = 8.dp) )
    }
}
@Composable
private fun EditBio(){
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxWidth()){
        OpaqueButton("Edit bio", onClick = {}, modifier= Modifier.defaultMinSize(minHeight = 8.dp) )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BioInputField(label : String, onChange : (String) -> Unit, onDone : (String) -> Unit = {}, startval : (String)){
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxWidth()){
    var text by remember { mutableStateOf(startval) }

    TextField(
        value = text,
        onValueChange = { text = it ; onChange(text) ; onDone(text)},
        label = { Text(label) },
        modifier = Modifier.padding(7.dp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0,0,0,0),
            textColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedIndicatorColor = Color(0xFFC5AC29),
            focusedIndicatorColor = Color(0xFF811C77)
            )
        )
    }
}
@Composable
private fun ChangeEmail(){
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxWidth()){
        OpaqueButton("Change email", onClick = {}, modifier= Modifier.defaultMinSize(minHeight = 8.dp) )
    }
}
@Composable
private fun ChangePassword(){
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxWidth()){
        OpaqueButton("Change password", onClick = {}, modifier= Modifier.defaultMinSize(minHeight = 8.dp) )
    }
}