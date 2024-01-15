package com.example.synema.view.screens

import GradientBox
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.model.ActorModel
import com.example.synema.model.CreditsModel
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.WatchlistModel
import com.example.synema.view.components.ActorPicture
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.DarkGradient
import com.example.synema.view.components.InlineIcon
import com.example.synema.view.components.LoadingWrapper
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.MovieClip
import com.example.synema.view.components.OpaqueButton
import com.example.synema.view.components.TitleFont
import com.example.synema.view.components.TopBar
import com.example.synema.view.utils.Size
import com.example.synema.viewmodel.media.ActorDetailsViewModel
import com.example.synema.viewmodel.media.MediaDetailsViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ActorDetails(
    actorID: String?
) {

    val vm : ActorDetailsViewModel = viewModel()

    if (actorID != null) {
        vm.init(actorID)
    } else {
        vm.back()
    }
    //val movie : MovieModel = source.loadMovie(movieID.toString())
    DarkGradient {
        Column {
            TopBar(
                "Synema",
                Alignment.Center,
                20.sp,
                backArrow = true,
                navController = vm.getNav()
            )

            LoadingWrapper(vm.isLoading) {
                MainContainer(hasBottomNav = true) {
                    TitleFont(vm.actor.value.name)
                    Row (Modifier.padding(10.dp)){
                        if(vm.actor.value.pic != "") {
                            ActorPicture(vm.actor.value.pic)

                        }
                        DetailsSection(vm.actor.value)
                    }
                    if(!vm.moviesStarring.isEmpty()){
                        MoviesSection(vm = vm)
                    }
                    if(vm.actor.value.bio != ""){
                        DescriptionSection(vm.actor.value.bio)
                    }
                    /*
                    SimilarMoviesSection(vm)
                    if(!vm.reviewList.isEmpty()){
                        UserReviewSection(vm.reviewList)
                    }
                     */
                }
                BottomBar(navController = vm.getNav())

            }





        }
    }
}
@Composable
private fun DetailsSection(actor : ActorModel) {

    Column (modifier = Modifier
        .padding(10.dp)
        .height(120.dp), verticalArrangement = Arrangement.SpaceBetween) {
        Row{
            if(actor.pob != null){
                Text(text = "From " + actor.pob, color = Color.White, )
            }
        }

        if(actor.dob != null){
            Text(text = "Born on " + actor.dob, color = Color.White, )
        }
        if( actor.dod != null){
            Text(text = "Died on " + actor.dod, color = Color.White, )
        }
        if(actor.deps != null){
            Text(text = actor.deps, color = Color.White, fontStyle = FontStyle.Italic)
        }

    }
}

@Composable
private fun DescriptionSection(desc: String) {
    Text(
        "Biography",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 10.dp, start = 20.dp),
        color = Color.White
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color(0xFFB6842D))
    )
    Text(
        desc,
        textAlign = TextAlign.Justify,
        modifier = Modifier.padding(top = 3.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
        color = Color.White
    )

}

@Composable
private fun MoviesSection(vm: ActorDetailsViewModel){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color(0xFFB6842D))
    )
    MovieList(movieList = vm.moviesStarring, header = "Movies starring " + vm.actor.value.name , navController = vm.getNav())

}



@Composable
private fun MovieList(movieList: List<MovieModel>, modifier: Modifier = Modifier, header: String, navController : NavHostController) {

    var movList = movieList.shuffled();
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
    ) {
        Text(
            text = header,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
        )
        LazyRow(modifier = modifier) {
            items(movList) { movie ->
                MovieCard(
                    movie = movie,
                    modifier = Modifier.padding(8.dp),
                    navController
                )
            }
        }
    }
}


@Composable
private fun MovieCard(movie: MovieModel, modifier: Modifier = Modifier, navController : NavHostController) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp), // Customize the shape if needed
        color = Color(0x00000000) // Set the color to transparent
    ) {
        Column(
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(95.dp)
        ) {
            AsyncImage(
                model = movie.poster_url,
                contentDescription = null,
                modifier = Modifier
                    .width(95.dp)
                    .height(135.dp)
                    .clickable { navController.navigate("mediaDetails/" + movie.id) },
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = movie.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                lineHeight = 12.sp,
                textAlign = TextAlign.Center

            )
        }

    }
}


