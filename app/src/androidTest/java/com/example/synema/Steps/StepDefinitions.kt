package com.example.synema.Steps

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class StepDefinitions {
    @Given("the app is running")
    fun givenTheAppIsRunning() {
        // Implementation for this step
        println("Step 1: The app is running")
    }

    @When("I perform some action")
    fun whenIPerformSomeAction() {
        // Implementation for this step
        println("Step 2: I perform some action")
    }

    @Then("I should see the expected result")
    fun thenIShouldSeeTheExpectedResult() {
        // Implementation for this step
        println("Step 3: I should see the expected result")
    }
}