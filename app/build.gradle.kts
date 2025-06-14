
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.crypto"
    compileSdk = 35
    ndkVersion = "28.0.12433566"
    packaging{
        resources {
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }


    defaultConfig {
        applicationId = "com.example.crypto"
        minSdk = 27
        //noinspection OldTargetApi
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.coincap.io/v2\"")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://api.coincap.io/v2\"")
        }
        }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    hilt {
        enableAggregatingTask = false
    }
    ksp {
        arg("dagger.hilt.disableModulesHaveInstallInCheck", "true")
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
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.storage)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.bundles.retrofit)

    implementation(libs.room)
    ksp(libs.bundles.room)

    implementation(libs.bundles.datastore)

    implementation(libs.daggerhilt)
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.android.compiler)
    androidTestImplementation(libs.hilt.testing)
    androidTestAnnotationProcessor(libs.hilt.compiler)
    testImplementation(libs.hilt.testing)
    testAnnotationProcessor(libs.hilt.compiler)

    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.lifecycle)

    implementation(libs.picasso)

    implementation(libs.gson)
    implementation(libs.shimmerRecyclerView)
    implementation(libs.jsoup)
    implementation(libs.lottie)
    implementation(libs.bundles.pager)
    implementation(libs.chart)

    implementation(libs.bundles.moshi)
    ksp(libs.moshikapt)

}

