package com.example.synema.view.screens

import GradientBox
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.synema.R
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.OpaqueButton
import com.example.synema.view.components.TopBar

@Composable
fun EditProfile(navController: NavHostController, profileState: MutableState<ProfileModel>) {

    var updateName by remember { mutableStateOf(profileState.value.name) }
    var updateBio by remember { mutableStateOf(profileState.value.bio) }
    var updatePic by remember { mutableStateOf(profileState.value.profilePicture) }

    GradientBox {
        Column {
            TopBar(title = "Edit Profile", Alignment.Center)
            MainContainer() {
                ProfilePicture(profileState.value) { selectedImageUri ->
                    updatePic = selectedImageUri.toString()
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OpaqueButton("Cancel", onClick = {
                        updateName = profileState.value.name
                        updateBio = profileState.value.bio
                        updatePic = profileState.value.profilePicture

                        navController.popBackStack()
                    })

                    OpaqueButton("Save", onClick = {
                        // Update the original profileState with the edited fields when saving changes
                        profileState.value = profileState.value.copy(
                            name = updateName,
                            bio = updateBio,
                            profilePicture = updatePic
                        )

                        // Navigate back to the profile screen
                        navController.popBackStack()
                    })
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
                        disabledTextColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

            }
            BottomBar(navController = navController )
        }
    }
}





@Composable
fun ProfilePicture(
    currentUser: ProfileModel,
    imageUrl: String = "https://postimg.cc/4nzy4Qwy",
    onImageSelected: (Uri) -> Unit = {}
) {
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
                    if (imageUrl.isNotEmpty()) {
                        onImageSelected(Uri.parse(imageUrl))
                    }
                }
        ) {
            if (imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                AsyncImage(
                    model = currentUser.profilePicture,
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







/*
@Composable
private fun ProfilePicture(currentUser: ProfileModel) {

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
                model = currentUser.profilePicture, contentDescription = null, modifier = Modifier
                    .size(145.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun ProfileImage(currentUser: ProfileModel){
    val imageUri = rememberSaveable{ mutableStateOf("") }
    val painter = rememberImagePainter(
        if(imageUri.value.isEmpty())
        R.drawable.profile_picture_placeholder
        else
        imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()
    ){ uri: Uri? ->
        uri?.let{imageUri.value = it.toString()}
    }
    println(currentUser.name)
    println(currentUser.profilePicture)
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        ){
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .clickable{launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )
        }
    }
}
*/
 */
