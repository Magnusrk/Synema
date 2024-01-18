package com.example.synema.view.screens

import GradientBox
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.model.CreditsModel
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.WatchlistModel
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
import com.example.synema.viewmodel.media.MediaDetailsViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MediaDetails(
    movieID: String?
) {

    val vm : MediaDetailsViewModel = viewModel()

    if (movieID != null) {
        vm.init(movieID)
    } else {
        vm.back()
    }





   // println(actorList[0])


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
                    MovieClip(vm.movie.value.backdrop_url)
                    TitleFont(vm.movie.value.title)
                    InteractionPane(vm)
                    ActorList(vm)
                    DescriptionSection(vm.movie.value.description)
                    ImageRoll(vm)
                    SimilarMoviesSection(vm)
                    if(!vm.reviewList.isEmpty()){
                        UserReviewSection(vm)
                    }
                }

            }
            BottomBar(navController = vm.getNav())





        }
    }
}

@Composable
fun InteractionPane(
    vm : MediaDetailsViewModel
) {
    val size = Size();
    Text(
        text = vm.movie.value.tagline,
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        color = Color.White,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        lineHeight = 15.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height((size.height() / 5).dp)
    ) {
        SaveButton(vm)
        RatingPanel(vm)
    }

}

@Composable
fun SaveButton(vm : MediaDetailsViewModel) {
    val size = Size();
    Column(
        modifier = Modifier
            .width((size.width() / 2).dp)
            .padding(horizontal = 15.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = vm.movie.value.release_date,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(start = 4.dp, top = 20.dp, bottom = 10.dp)
        )
        Button(
            onClick = { vm.saveMovie() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB15FA8)),
            shape = RoundedCornerShape(20),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Save")
                InlineIcon(resourceID = R.drawable.playlist_add)
            }
        }
        Box(modifier = Modifier.fillMaxSize())
    }

}


@Composable
fun RatingPanel(
    vm : MediaDetailsViewModel
) {
    val size = Size();
    var avg = 0f
    Column(
        modifier = Modifier
            .width((size.width() / 2).dp)
            .padding(horizontal = 15.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center

    ) {
        Button(
            onClick = {},
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF430B3D)),
            contentPadding = PaddingValues(horizontal = 5.dp),

            ) {
            RatingStars(vm.movie.value.rating)
        }




        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.75f),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF430B3D)),
            contentPadding = PaddingValues(horizontal = 15.dp)
        ) {
            if (!vm.reviewList.isEmpty()) {
                vm.reviewList.forEach() { review -> avg += review.rating }


                avg /= vm.reviewList.size;


            }
            val solution: Double = avg.toDouble()
            Text("User ratings: " + solution.toString() + "/5")
        }

        Button(
            onClick = { vm.reviewMovie() },
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF430B3D)),
            contentPadding = PaddingValues(horizontal = 15.dp)
        ) {
            Row {
                Text("Review", modifier = Modifier.padding(top = 4.dp))
                InlineIcon(resourceID = R.drawable.edit_playlist)
            }

        }

    }

}


@Composable
private fun RatingStars(rating: Number) {

    val starImage = ImageBitmap.imageResource(id = R.drawable.stars6)
    //val bigStarImage = ImageBitmap.imageResource(id = R.drawable.stars_big)

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth(rating.toFloat() / 10)

        ) {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {

                drawImage(image = starImage)
            }
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(alpha = 0.99f)
            ) {

                drawRect(
                    color = Color(0xFFB6842D),
                    size = size,
                )
                drawImage(image = starImage, blendMode = BlendMode.DstAtop)
            }
        }
    }
}

@Composable
private fun ReviewStars(rating: Number) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        for (n in 1..5) {
            if (rating.toFloat() / 2 >= n.toFloat()) {
                InlineIcon(
                    resourceID = R.drawable.icon_star, size = 20.dp, spacing = 2.dp, tint = Color(
                        0xFFB6842D
                    )
                )
            } else {
                InlineIcon(resourceID = R.drawable.icon_star, size = 20.dp, spacing = 2.dp)
            }
        }
    }
}


@Composable
private fun DescriptionSection(desc: String) {
    Text(
        "Description",
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
private fun UserReviewSection(vm: MediaDetailsViewModel) {

    Text(
        "User reviews",
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

    Column() {
        vm.reviewList.forEach() { review -> UserReviewCard(vm,review) }
    }

}

@Composable
private fun UserReviewCard(vm: MediaDetailsViewModel, review: ReviewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(70.dp)
            .padding(horizontal = 15.dp, vertical = 10.dp)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF430B3D), shape = RoundedCornerShape(10.dp))

        ) {
            InnerReviewContainer(vm, review)
        }

    }
}

@Composable
private fun InnerReviewContainer(vm: MediaDetailsViewModel, review: ReviewModel) {
    var expanded by remember { mutableStateOf(false) }
    var moreText by remember {
        mutableStateOf("More")
    }

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            ClickableText(
                text = AnnotatedString("@" + review.username),
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                ),
                onClick = {
                    vm.getNav().navigate("ouprofiles/"+review.userid)
                })

            ReviewStars(review.rating * 2)
        }

        if (expanded) {

            Text(
                review.reviewText,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
            )
        } else {
            Text(
                review.reviewText,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (review.reviewText.length > 30) {
                OpaqueButton(
                    label = moreText,
                    onClick = {
                        expanded = !expanded;
                        if (expanded) {
                            moreText = "Less"
                        } else {
                            moreText = "More"
                        }
                    },
                    Modifier.defaultMinSize(minHeight = 5.dp)
                )
            }
        }

    }
}

@Composable
private fun ActorList(vm : MediaDetailsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
    ) {
        Text(
            text = "",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
        )
        LazyRow(modifier = Modifier) {
            items(vm.actorList) { actor ->
                ActorCard(
                    actor = actor,
                    modifier = Modifier.padding(8.dp),
                    vm.getNav()
                )
            }
        }
    }
}

@Composable
fun ActorCard(actor: CreditsModel, modifier: Modifier = Modifier, navController : NavHostController) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp), // Customize the shape if needed
        color = Color(0xFF430B3D)
    ) {
        Column (
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(95.dp)
        ){
            AsyncImage(
                model = actor.picture_path,
                contentDescription = null,
                modifier = Modifier
                    .width(95.dp)
                    .height(135.dp)
                    .clickable {
                        navController.navigate("actor/" + actor.id)
                    },
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier =  Modifier.height(5.dp))
            Text(
                text = actor.character,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                minLines = 2,
                lineHeight = 12.sp,
                textAlign = TextAlign.Center,

            )
            Text(
                text = actor.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                minLines = 2,
                lineHeight = 12.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier =  Modifier.height(5.dp))
        }

    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ImageRoll(vm: MediaDetailsViewModel){
    if (!vm.imagesList.isEmpty()) {
        Text(
            "Media",
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
            var state = rememberLazyListState()
            LazyRow(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth().defaultMinSize(minHeight = 200.dp),
                state = state,
                flingBehavior = rememberSnapFlingBehavior(lazyListState = state),
            ) {
                items(vm.imagesList) { image ->
                    AsyncImage(
                        model = image.file_path,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.width(325.dp)
                    )
                }
            }
        }

    }


@Composable
private fun SimilarMoviesSection(vm: MediaDetailsViewModel){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color(0xFFB6842D))
        )
    MovieList(movieList = vm.similarMovies, header = "Movies similar to " + vm.movie.value.title , navController = vm.getNav())

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


