// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val agp_version by extra("7.3.1")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.hiltAndroid)
        classpath(AndroidX.navigation_safe_arg)
        classpath ("io.realm.kotlin:gradle-plugin:1.8.0")
        classpath("com.android.tools.build:gradle:$agp_version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    }
}
