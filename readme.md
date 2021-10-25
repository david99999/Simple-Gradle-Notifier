# Simple Gradle Notifier Plugin

#### This plugins provides a convenient way for performing tasks after specific gradle tasks finishes or fails

## Adding it into IntelliJ IDE's usin plugin DSL

```sh
plugins {
    ...
   id("io.github.david99999.gradle.notifier") version "0.0.1"
    ...
}
```

## Or using legacy plugin application

```sh
buildscript {
  repositories {
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
  }
  dependencies {
    classpath("io.github.david99999.gradle:NotifierPlugin:0.0.1")
  }
}

apply(plugin = "io.github.david99999.gradle.notifier")
```

## Configuring task monitoring

#### You can use the `gradleNotifierConfig`extension if you want to execute a specific command after a task has finished

#### e.g: Watching success task execution of generic android app compilation ("assembleDebug")

```sh
dependencies {
    ...
}

gradleNotifierConfig {
    taskForWatchingSuccess = "assembleDebug"
    taskForWatchingFailure = "compileDebugKotlin"
    successCommandArguments = ["say", "Hello", "hello", "my", "friend,", "your", "task", "has", "finished"]
    failureCommandArguments = ["afplay", "-t", "3", "/Users/david/Downloads/failure.mp3"]
}
```