apply {
    from("$rootDir/android-library-build.gradle")
}
dependencies {

    "implementation"(project(":base"))
    "implementation"(project(":post:data-source"))
    "implementation"(project(":gateway"))
    "implementation"(project(":database"))

    "implementation"(DataStore.dataStore)
    "implementation"(DataStore.dataStorePreferencesCore)

}