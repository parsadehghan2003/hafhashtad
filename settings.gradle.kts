pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "hafhashtad"
include(":app")
include(":base")
include(":navigator")
include(":base-android")

include(":gateway")
include(":database")

include(":resource")

include(":post")
include(":post:domain")
include(":post:data-source")
include(":post:framework")
include(":post:usecase")
include(":post:ui")
 