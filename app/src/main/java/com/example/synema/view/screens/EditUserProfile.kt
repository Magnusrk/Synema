package com.example.synema.view.screens

import GradientBox
import android.widget.Button
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.OpaqueButton
import com.example.synema.view.components.TopBar


@Composable
fun EditProfile(navController: NavHostController, profileState: MutableState<ProfileModel>) {
    val dataSource = DependencyProvider.getInstance().getUserSource()
    var updateName by remember { mutableStateOf(profileState.value.name) }
    var updateBio by remember { mutableStateOf(profileState.value.bio) }
    var updatePic by remember { mutableStateOf(profileState.value.profilePicture) }

    GradientBox {
        Column {
            TopBar(title = "Edit Profile", Alignment.Center)
            MainContainer() {
                //EditProfilePicture(profileState.value, navController)
                //AvatarList(navController, profileState = profileState)
                ProfilePicture(
                    currentUser = ProfileModel(
                        name = updateName,
                        bio = updateBio,
                        profilePicture = updatePic
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            updateName = profileState.value.name
                            updateBio = profileState.value.bio
                            updatePic = profileState.value.profilePicture
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF941F1F)),
                    ) {
                        Text(text = "Cancel")
                    }

                    Button(
                        onClick = {
                            profileState.value.bio = updateBio
                            dataSource.editbio(
                                profileState.value.id,
                                updateBio,
                                profileState.value.token
                            ) {
                                navController.popBackStack()
                            }
                            profileState.value.name = updateName
                            dataSource.editusername(
                                profileState.value.id,
                                updateName,
                                profileState.value.token
                            ) {

                            }
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF74306D)),
                    ) {
                        Text(text = "Save")
                    }

                }
                // Text field for editing name
                TextField(
                    value = updateName,
                    maxLines = 1,
                    onValueChange = { updateName = it },
                    label = { Text("Name") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF543b5b),
                        unfocusedContainerColor = Color(0xFF543b5b),
                        disabledContainerColor = Color(0xFF736477),
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White,
                        focusedLabelColor = Color.White,
                        disabledLabelColor = Color.White,
                        errorLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                        //disabledTextColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                // Text field for editing bio
                TextField(
                    value = updateBio,
                    maxLines = 6,
                    onValueChange = { updateBio = it },
                    label = { Text("Bio") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF543b5b),
                        unfocusedContainerColor = Color(0xFF543b5b),
                        disabledContainerColor = Color(0xFF736477),
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White,
                        disabledTextColor = Color.LightGray,
                        focusedLabelColor = Color.White,
                        disabledLabelColor = Color.White,
                        errorLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            BottomBar(navController = navController)
        }
    }

}

/*
    @Composable
    fun ProfilePicture(currentUser: ProfileModel) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), horizontalArrangement = Arrangement.Center
        ) {
            println(currentUser.name)
            println(currentUser.profilePicture)
            if (false) {
                Image(
                    painter = painterResource(R.drawable.profile_picture_placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .size(145.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Black, CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                println(currentUser.bio)
                AsyncImage(
                    model = currentUser.profilePicture,
                    contentDescription = null,
                    modifier = Modifier
                        .size(145.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Black, CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}



@Composable
fun EditProfilePicture(
    currentUser: ProfileModel,
    navController: NavHostController
)
{
    var updatePic by rememberSaveable { mutableStateOf(currentUser.profilePicture) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(145.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Black, CircleShape)
                .clickable {
                    //navController.navigate("avatars")
                }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (updatePic.isNotEmpty()) {
                    AsyncImage(
                        model = updatePic,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.profile_picture_placeholder),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}



*/