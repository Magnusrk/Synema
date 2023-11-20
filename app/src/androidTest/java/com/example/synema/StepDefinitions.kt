package com.example.synema

import androidx.test.platform.app.InstrumentationRegistry
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.*
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice


class StepDefinitions {
    @Given("I am on the home screen")
    fun givenTheAppIsRunning() {
        // Implementation for this step
        println("tester")
        /*

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        val button = device.findObject(By.text("HomeScreen"))

        button.click()

         */
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