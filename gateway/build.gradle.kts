apply {
    from("$rootDir/android-library-build.gradle")
}
dependencies {

    "implementation"(project(":base"))

}