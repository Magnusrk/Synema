package com.example.synema.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.synema.Data.DataStore.DataStoreManager
import com.example.synema.Data.DependencyProvider
import com.example.synema.controller.AppContext
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.UserModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Period
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.seconds

class FeedViewModel : ViewModel() {

    var context = AppContext.getInstance()
    val source = DependencyProvider.getInstance().getUserSource()
    var followerCount = mutableIntStateOf(0)
    var followerList = mutableStateListOf<String>()
    var reviewList = mutableStateListOf<ReviewModel>()
    val profileState = AppContext.getInstance().getProfileState().value

    val movieSource = DependencyProvider.getInstance().getMovieSource()

    val isLoading = mutableStateOf(true)

    private val nav = AppContext.getInstance().getNav()




    @RequiresApi(Build.VERSION_CODES.O)
    fun init(profileModel: ProfileModel){
        isLoading.value = true
        getListOfFollowers(profileModel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFollowerReviews(){
        reviewList.clear()
        for (user in followerList) {
            movieSource.getOtherUserReviews(user, profileState.token) {
                if (it.successful()) {
                    it.getResult()?.let { reviewModel ->
                        reviewList.addAll(reviewModel)
                        sortReviews()

                    }
                }
                isLoading.value = false
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sortReviews(){
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        //reviewList.sortWith(Comparator.comparing { a -> LocalDate.parse(a.date, dateTimeFormatter).toEpochDay().seconds.absoluteValue })
        try {
            reviewList.sortBy {
                -LocalDateTime.parse(it.date, dateTimeFormatter).toInstant(ZoneOffset.UTC).epochSecond
            }.let {
                //reviewList.reverse()
            }

        } catch(e : Error ) {
            context.getNav().navigate("home")
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListOfFollowers(profileModel: ProfileModel){
        source.getFollowing(profileModel.id, profileState.token) {
            if (it.successful()) {
                it.getResult()?.let { profile ->
                    followerList.clear()
                    followerList.addAll(profile)
                    followerCount.value = profile.size
                    getFollowerReviews()
                }
            }
        }
    }


    fun getNav() : NavHostController {
        return nav
    }

}