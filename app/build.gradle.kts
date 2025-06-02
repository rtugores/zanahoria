plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "huitca1212.cuantotemide"
    compileSdk = 36

    defaultConfig {
        applicationId = "huitca1212.cuantotemide"
        minSdk = 23
        targetSdk = 34
        versionCode = 11
        versionName = "2.2.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    signingConfigs {
        signingConfigs {
            create("release") {
                keyAlias = "cuantotemide"
                keyPassword = "Password01"
                storeFile = file("../key/key.keystore")
                storePassword = "Password01"
            }
        }
    }
    buildTypes {
        getByName("debug") {
            resValue("string", "ads_banner_id", "ca-app-pub-3940256099942544/6300978111")
            isDebuggable = true
            isMinifyEnabled = false
        }
        getByName("release") {
            resValue("string", "ads_banner_id", "ca-app-pub-9645153779384738/6043080407")
            isDebuggable = false
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles("../proguard-project-release.txt")
            signingConfig = signingConfigs.getByName("release")
        }
    }
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.appcompat.core)
    implementation(libs.androidx.core)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.uiTooling)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.lifecycle.runtimeCompose)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    implementation(libs.materialComponents)

    implementation(libs.play.services.ads)

    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core.ktx)
    testImplementation(libs.test.runner)
    testImplementation(libs.robolectric)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.okhttp3.mockWebServer)
    testImplementation(libs.assertJ)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}
