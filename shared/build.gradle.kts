plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose") version "1.3.0"
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
    iosX64()
    iosArm64()
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
        val iosX64Main by getting{
            dependsOn(iosMain)
        }
        val iosArm64Main by getting{
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Main by getting{
            dependsOn(iosMain)
        }


        val iosTest by getting {
            dependsOn(commonTest)
        }
        val iosX64Test by getting{
            dependsOn(iosTest)
        }
        val iosArm64Test by getting{
            dependsOn(iosTest)

        }
        val iosSimulatorArm64Test by getting{
            dependsOn(iosTest)
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
