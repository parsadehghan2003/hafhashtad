plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.ftpd.hafhashtad"
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = "com.ftpd.hafhashtad"
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(Google.material)
    implementation(AndroidX.constraintlayout)
    implementation(project( ":navigator"))
    implementation(project(":base-android"))
    implementation(project(":post:ui"))
    testImplementation(JUnit.jUnit)
    androidTestImplementation(AndroidXTest.jUnitInstrumentTest)
    androidTestImplementation(AndroidXTest.espresso)
    implementation(Hilt.android)
    kapt(Hilt.compiler)

}