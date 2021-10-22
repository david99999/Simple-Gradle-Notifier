import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.4.10"
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "0.14.0"
}

group = "io.github.david99999.gradle"
version = "0.0.1"

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.10")
    compileOnly(gradleApi())
    testImplementation("junit", "junit", "4.12")
}

pluginBundle {
    website = "https://github.com/david99999/Simple-Gradle-Notifier"
    vcsUrl = "https://github.com/david99999/Simple-Gradle-Notifier"
    tags = listOf("task monitoring", "task reporting", "task notification")
}

gradlePlugin {
    plugins {
        register("notifier") {
            id = "io.github.david99999.gradle.notifier"
            displayName = "Simple Gradle Notifier Plugin"
            description =
                "This plugins provides a convenient way for performing tasks after specific gradle tasks finishes or fails"
            implementationClass = "io.github.david99999.gradle.NotifierPlugin"
        }
    }
}

publishing {
    repositories {
        mavenLocal()
    }
}