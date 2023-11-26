import com.android.build.api.dsl.Packaging

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}




android {
    namespace = "com.example.synema"
    compileSdk = 33


    packagingOptions {
        resources.excludes.add("META-INF/*")
    }



    defaultConfig {
        applicationId = "com.example.synema"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner="androidx.test.runner.AndroidJUnitRunner"





        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests {
            isReturnDefaultValues = true;
            isIncludeAndroidResources = true;
        }
    }
}

dependencies {



    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("androidx.test.uiautomator:uiautomator:2.2.0")
    testImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")


    testImplementation ("io.cucumber:cucumber-android:7.14.0")
    testImplementation ("io.cucumber:cucumber-junit:7.14.0")


    testImplementation(platform("io.cucumber:cucumber-bom:7.14.0"))
    testImplementation("io.cucumber:cucumber-junit-platform-engine")
    testImplementation("io.cucumber:cucumber-java:7.14.0")
    testImplementation("io.cucumber:cucumber-junit:7.14.0")




    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.robolectric:robolectric:4.11.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    testImplementation("androidx.compose.ui:ui-test-junit4")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    val nav_version = "2.5.0"
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.compose.ui:ui-text-google-fonts:1.4.3")
    implementation("io.coil-kt:coil-compose:2.0.0-rc03")
    implementation("io.coil-kt:coil:2.0.0-rc03")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
    testImplementation("io.cucumber:cucumber-picocontainer:4.8.1")
    testImplementation("com.google.dagger:hilt-android-testing:2.45")
    // Required -- JUnit 4 framework
    testImplementation("junit:junit:4.13.1")
    testImplementation("androidx.test:core-ktx:1.3.0")
    testImplementation("androidx.test.ext:junit-ktx:1.1.2")
    testImplementation("org.robolectric:robolectric:4.4")
    testImplementation("androidx.test.ext:truth:1.3.0")
    testImplementation("com.google.truth:truth:1.0")
    testImplementation("org.mockito:mockito-core:3.3.3")
    testImplementation("androidx.test.ext:junit-ktx:1.1.2")
    testImplementation("org.robolectric:robolectric:4.")






}