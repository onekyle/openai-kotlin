group = "com.aallam.openai"
version = "0.1.0"

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.vanniktech.maven.publish")
    id("binary-compatibility-validator")
    id("com.diffplug.spotless")
    id("org.jetbrains.dokka")
    id("build-support")
//    id("com.android.library")
//    id("org.jetbrains.kotlin.native.cocoapods")
//    alias(libs.plugins.kotlinCocoapods)
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.androidLibrary)
}

kotlin {

//    androidTarget()

    explicitApi()
    jvm()
//    jsNode()
    native()

//    iosSimulatorArm64("native") {
//        binaries {
//            framework {
//                baseName = "openai-kotlin"
//            }
//        }
//    }

    sourceSets {
        all {
            languageSettings {
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("okio.ExperimentalFileSystem")
                optIn("com.aallam.openai.api.ExperimentalOpenAI")
                optIn("com.aallam.openai.api.BetaOpenAI")
                optIn("com.aallam.openai.api.InternalOpenAI")
                optIn("com.aallam.openai.api.LegacyOpenAI")
            }
        }
        val commonMain by getting {
            dependencies {
                api(projects.openaiCore)
                api(libs.coroutines.core)
                api(libs.okio)
                implementation(libs.serialization.json)
                api(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.auth)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.serialization.json)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(projects.openaiCore)
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(libs.coroutines.test)
                implementation(libs.okio.fakefilesystem)
                implementation(libs.ulid)
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(libs.ktor.client.okhttp)
                implementation(libs.logback.classic)
            }
        }

//        val jsMain by getting {
//            dependencies {
//                implementation(libs.okio.nodefilesystem)
//            }
//        }
//        val jsTest by getting {
//            dependencies {
//                implementation(kotlin("test-js"))
//            }
//        }
//        val desktopTest by getting {
//            dependencies {
//                implementation(libs.ktor.client.curl)
//            }
//        }
//        val darwinTest by getting {
//            dependencies {
//                implementation(libs.ktor.client.darwin)
//            }
//        }
    }
}

// Disabling test compilation and execution
//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    if (name.contains("Test")) {
//        enabled = false
//    }
//}

//tasks.all {
//    if (name.contains("Test")) {
//        enabled = false
//    }
//}
