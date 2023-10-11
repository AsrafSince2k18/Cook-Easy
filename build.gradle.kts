// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.6.0"

        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.46.1")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.8.20")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.1")

    }


}


plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("com.google.dagger.hilt.android") version "2.46.1" apply false

}