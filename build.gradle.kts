import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.7.10"
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

val libVersion by extra("2.4.0")

group = "com.pi4j"
version = libVersion

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

nexusPublishing {
    repositories {
        sonatype()
    }
}