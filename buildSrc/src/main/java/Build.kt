object Build {
    private const val androidBuildToolsVersion = "7.2.1"
    private const val betterLinkVersion = "2.2.0"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"

    const val hiltAndroid = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.hiltVersion}"

    const val betterLink = "me.saket:better-link-movement-method:$betterLinkVersion"

    const val androidLibrary = "com.android.library:7.1.3"

    const val androidApplication = "com.android.application:7.1.3"

}