# Albums App

## Description of the app:
- Shows a list of albums title + thumbnail from https://static.leboncoin.fr/img/shared/technical-test.json
- Shows cached data from database on offline mode
- Must handle screen rotations
- Must load images with an "User-Agent" header

## Architecture, patterns and libraries choices:
- Clean architecture with multi-modules: we have several modules for core : app, domain, network, ui. 
Then we have several modules for the "album" feature : data-database, data-network and ui.
This app is simple so a multi-module architecture is a bit overkill, but it forces the code to be neatly separated in the right layer.
- Gradle version catalogs to share dependencies between modules
- Hilt for the dependency injection
- Coroutines and Flows for async tasks
- MVVM with a StateFlow that emits an UiState that represents the state of the UI.
- Compose for the UI, with a preview of each state
- Room database
- Retrofit + Okhttp for the network requests
- Coil for loading images: we inject a custom ImageLoader in `NetworkModule` to set the "User-Agent" header in the requests, plus it works well with Compose
- Unit testing with JUnit and fakes (no mocks so it’s bit faster)
- `GetAlbumsUseCase` contains the business logic of switching between the network data source and the database data source.
- Android tests of the `AlbumsScreen` and the `MainNavigation`
- Supports dark mode.

## Remarks
- Ideally the class CoroutinesTestRule should be in the core-app/testFixtures folder, but does not work :( https://youtrack.jetbrains.com/issue/KT-50667/Support-compiling-android-testFixtures-sources-in-the-kotlin-android-gradle-plugin
- We could have another module for testing purposes `test-app` and move the `MainNavigationTest` and `AndroidTestActivity` inside
- It’s the bare minimum UI. We could improve a bunch of things, with a better theme, an app icon, animations, a design system, etc. 
