# Using Hilt in your Android app

An application that logs user's interactions on buttons and displays them in a `Fragment`, built by following the instructions detailed in the Google Codelab **["Using Hilt in your Android app"][Android_Hilt_Codelab]**. Original code by Google for this codelab can be referred [here][Android_Hilt_Repository].

## What one will learn

* How to use Hilt in your Android app.
* How to set up Hilt in your Application class using `@HiltAndroidApp`.
* How to add dependency containers to the different Android lifecycle components using `@AndroidEntryPoint`.
* How to use Modules to tell Hilt how to provide certain types like interfaces and others which do not allow/support constructor injection.
* How to Scope bindings to a specific Container/Component.
* How to add multiple bindings to the same type with Qualifiers.
* How to execute Instrumented tests with Hilt to test an application that uses Hilt for Dependency Injection.
* How to use `@EntryPoint` to access containers from classes that Hilt does not support.

## Getting Started

* Android Studio Arctic Fox or higher with updated SDK and Gradle.

### Prerequisites

* Some experience with Kotlin syntax.
* Understanding of why dependency injection is important in an application.
* Understanding of Room database and ContentProvider.

## Branches in this Repository

* **[starter-code](https://github.com/kaushiknsanji/android-hilt/tree/starter-code)**
	* This is the Starter code for the [codelab][Android_Hilt_Codelab].
	* In comparison to the original [main][Android_Hilt_Starter_Repository] repository, this repository uses Android `ViewBinding` to bind UI elements with its objects.
* **[master](https://github.com/kaushiknsanji/android-hilt/tree/master)**
	* This contains the Solution for the [codelab][Android_Hilt_Codelab].
	* In comparison to the original [solution][Android_Hilt_Solution_Repository] repository, this repository contains additional modifications and corrections-
		* Uses Idiomatic Kotlin approaches in the code for [LogsContentProvider](https://github.com/kaushiknsanji/android-hilt/blob/master/app/src/main/java/com/example/android/hilt/contentprovider/LogsContentProvider.kt).
		* Includes Provider element for `LogsContentProvider` in [AndroidManifest.xml](https://github.com/kaushiknsanji/android-hilt/blob/e5497a0a151dfae046d36b7e90cf3276ab9c8808/app/src/main/AndroidManifest.xml#L40).

# License

```
Copyright (C) 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

<!-- Reference Style Links are to be placed after this -->
[Android_Hilt_Codelab]: https://developer.android.com/codelabs/android-hilt
[Android_Hilt_Repository]: https://github.com/googlecodelabs/android-hilt
[Android_Hilt_Starter_Repository]: https://github.com/googlecodelabs/android-hilt/tree/main
[Android_Hilt_Solution_Repository]: https://github.com/googlecodelabs/android-hilt/tree/solution