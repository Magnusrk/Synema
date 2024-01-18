package com.example.synema.view.screens

import GradientBox
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.ui.theme.SynemaTheme
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.LoadingWrapper
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.TopBar
import com.example.synema.viewmodel.FollowersViewModel
import com.example.synema.viewmodel.FollowingViewModel
import com.example.synema.viewmodel.SearchUsersViewModel
import com.example.synema.viewmodel.SearchViewModel




@Composable
fun FollowingScreen(userid: String?, navController: NavHostController, profileState: MutableState<ProfileModel>) {
    val vm: FollowingViewModel = viewModel()
    vm.initSearch(userid.toString())
    SynemaTheme {
            GradientBox {
                Column {
                    TopBar(
                        "Following",
                        Alignment.CenterStart,
                        20.sp,
                        backArrow = true,
                        transparent = true,
                        search = false,
                        textInput = false,
                        navController = navController,
                    )
                    MainContainer(hasBottomNav = true, scrollAble = false) {
                        followingList(vm)
                    }
                    BottomBar(navController = navController)
                }
            }
        }
    }

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun followingList(vm: FollowingViewModel) {


    LazyColumn{
                items(vm.usersList.size) { index ->//change this to userList
                    followingCard(
                        user = vm.usersList[index],
                        modifier = Modifier.padding(8.dp),
                        vm.getNav()
                    )
                }
            }
        }



@Composable
fun followingCard(user: ProfileModel, modifier: Modifier = Modifier, navController : NavHostController) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp), // Customize the shape if needed
        color = Color(0x00000000) // Set the color to transparent
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .width(500.dp)
                .height(50.dp)
                .background(Color(0xFF322236).copy(alpha = 0.3F))
                .clickable { navController.navigate("ouprofiles/"+user.id) }


        ){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                AsyncImage(
                    model = user.profilePicture,
                    contentDescription = null,
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color(0x00000000), CircleShape)
                    ,
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = user.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    lineHeight = 55.sp,

                )
            }
        }

    }
}

