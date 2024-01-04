import android.os.Build.VERSION_CODES.Q
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*

import com.example.synema.MainActivity
import org.junit.runner.RunWith

import androidx.test.runner.AndroidJUnitRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Q])
class StepDefs(val composeRuleHolder: ComposeRuleHolder, val scenarioHolder: ActivityScenarioHolder

): SemanticsNodeInteractionsProvider by composeRuleHolder.composeRule{


    @Given("I am on the home screen")
    fun givenTheAppIsRunning() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        scenarioHolder.launch(MainActivity.create(instrumentation.targetContext,null))

    }

    @When("I click on a movie")
    fun whenIPerformSomeAction() {
        // Implementation for this step
        println("Step 2: I perform some action")
    }

    @Then("I should see the media details screen")
    fun thenIShouldSeeTheExpectedResult() {
        // Implementation for this step
        println("Step 3: I should see the expected result")
    }


}