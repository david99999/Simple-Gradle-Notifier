# Simple Gradle Notifier Plugin
#### This plugins provides a convenient way for performing tasks after specific gradle tasks finishes or fails

## Adding it into IntelliJ IDE's

```sh
plugins {
    ...
    id 'com.simple.gradle.notifier' version "0.0.1"
    ...
}
```

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