import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinKsp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlinSerialization)
    id ("kotlin-kapt")

    alias(libs.plugins.protoBuf)
}
android {
    namespace = "com.johnnsantana.droidchat"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.johnnsantana.droidchat"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    viewBinding {
        enable = true
    }
    hilt {
        enableAggregatingTask = false
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.jwtdecode)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt("androidx.hilt:hilt-compiler:1.2.0")


    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.coil.compose)

    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.javalite)

    implementation (libs.ktor.client.core)
    implementation (libs.ktor.client.cio)
    implementation (libs.ktor.client.android)
    implementation (libs.ktor.client.websockets)
    implementation (libs.ktor.client.content.negotiation)
    implementation (libs.ktor.client.resources)
    implementation (libs.ktor.client.auth)
    implementation (libs.ktor.serialization.kotlinx.json)
    implementation (libs.ktor.client.logging)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)

    //    implementation(libs.firebase.bom)
    //    implementation("com.google.firebase:firebase-messaging")
    //    implementation(libs.accompanist.permissions)

    implementation(libs.exifinterface)
    implementation (libs.androidx.datastore.preferences)
    implementation(libs.security.crypto)


    implementation(libs.constraint.layout.compose)
    implementation(libs.constraint.layout.compose)
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)

    implementation(libs.compose.shimmer)
    implementation(libs.android.lottie.compose)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.4"
    }

    generateProtoTasks {
        all().configureEach {
            builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}