plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose") version "1.4.0-alpha01-dev954"
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    ios()
    iosX64("uikitX64") {
        binaries {
            executable() {
                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
            }
        }
    }

    iosSimulatorArm64()


    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:atomicfu:0.20.0")
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting

        val iosMain by getting {
            dependsOn(commonMain)
        }
        val uikitX64Main by getting{
            dependsOn(iosMain)
        }
        
        val iosSimulatorArm64Main by getting{
            dependsOn(iosMain)
        }

    }
}

android {
    namespace = "com.prasanth.composemultiplatapp"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}

compose.experimental {
    uikit.application {
        bundleIdPrefix = "prasanth.iosApp.compose"
        projectName = "ComposeMultiPlatApp"
        deployConfigurations {
            simulator("IPhone13Pro") {
                //Usage: ./gradlew iosDeployIPhone8Debug
                device = org.jetbrains.compose.experimental.dsl.IOSDevices.IPHONE_13_PRO
            }
            connectedDevice("Device") {
                //Usage: ./gradlew iosDeployDeviceRelease
                this.teamId="prasanthperumal92@gmail.com"
            }
        }
    }
}