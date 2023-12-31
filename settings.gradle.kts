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

rootProject.name = "Albums"
include(":app")
include(":core-domain")
include(":core-ui")
include(":core-network")
include(":albums-data-network")
include(":albums-data-database")
include(":albums-ui")
include(":core-app")
