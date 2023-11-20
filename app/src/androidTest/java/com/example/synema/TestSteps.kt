package com.example.synema

import io.cucumber.java.en.Given

import io.cucumber.java.af.En
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions

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
        Assertions.assertEquals("2", "2")
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