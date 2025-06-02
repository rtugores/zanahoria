// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.10.1" apply false
    alias(libs.plugins.ksp.plugin) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.compose.compiler) apply false
}
