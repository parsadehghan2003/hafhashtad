apply {
    from("$rootDir/android-library-build.gradle")

}
apply(plugin = "org.jetbrains.kotlin.android")
dependencies {
    "implementation"(project(":base-android"))
    "implementation"(project(":post:data-source"))
    "implementation"(project(":post:domain"))
    "implementation"(project(":post:framework"))
    "implementation"(project(":post:usecase"))
    "implementation"(project(":base"))
    "implementation"(project(":resource"))
}