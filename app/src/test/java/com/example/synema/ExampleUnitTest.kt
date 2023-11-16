package com.example.synema

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.synema.Data.DependencyProvider
import com.example.synema.model.MovieModel
import org.junit.Test


import org.junit.Assert.*
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    @Composable
    fun Tester(){
        val source = DependencyProvider.getInstance().getMovieSource()

        var movie : MovieModel by remember {
            mutableStateOf(MovieModel(
                    0,
                    "",
                    "",
                    "Loading...",
                    "Loading...",
                    0,
                    ""
            )
            )
        }

        source.loadMovie("872585"){
            movie = it.getResult()!!
        }
        assertEquals("Oppenheimer", movie.title)
    }


}
