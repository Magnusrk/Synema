package com.example.synema

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith


@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["app/src/androidTest/java/com/example/synema/Steps/resources"],
    glue = ["com.example.synema.Steps"]
)
class CucumberTestRunner {
}