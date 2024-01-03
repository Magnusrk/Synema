package com.example.synema

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.synema.Data.DependencyProvider
import com.example.synema.model.MovieModel

import androidx.compose.ui.test.junit4.createComposeRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.synema", appContext.packageName)
    }


}



@RunWith(AndroidJUnit4::class)  // To indicate that we've to run it with AndroidJUnit runner
class ExampleUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun testGettingMovieFromAPI() {
        composeTestRule.setContent {
            // Your Compose UI code goes here
            DependencyProvider.getInstance().create(false);

            val source = DependencyProvider.getInstance().getMovieSource()

            var movie: MovieModel by remember {
                mutableStateOf(
                    MovieModel(
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


            source.loadMovie("872585") {
                movie = it.getResult()!!
                // Your assertions go here
                assertEquals(872585, movie.id)
                assertNotEquals("", movie.poster_url)
                assertEquals("Oppenheimer", movie.title)
                assertNotEquals("Loading...", movie.description)
                assertNotEquals(0, movie.rating)
                assertNotEquals("", movie.release_date)
            }
        }
    }
}
