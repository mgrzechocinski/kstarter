import org.jetbrains.kotlin.config.KotlinCompilerVersion

/*
import org.codehaus.groovy.ast.tools.GeneralUtils.args
import org.gradle.internal.impldep.com.amazonaws.PredefinedClientConfigurations.defaultConfig
*/

plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-android-extensions")
  id("io.gitlab.arturbosch.detekt").version("1.0.0.RC9.2")
}

android {
  compileSdkVersion(28)

  defaultConfig {
    applicationId = "me.grzechocinski.kstarter"
    minSdkVersion(21)
    targetSdkVersion(28)
    versionCode = 1
    versionName = "1.0"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }
}

detekt {
  input = files(
    "src/main/java",
    "src/test/java"
  )
  config = files(project.rootDir.resolve("detekt.yml"))
  reports {
    html.enabled = true
  }
}

dependencies {
  implementation("com.android.support:appcompat-v7:28.0.0")
  implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
  implementation("com.jakewharton.threetenabp:threetenabp:1.0.5")

  testImplementation("com.github.memoizr:assertk-core:1.0.0-beta.2") {
    exclude("org.jetbrains.kotlin")
  }
  testImplementation("junit:junit:4.12")
  testImplementation("org.mockito:mockito-core:2.13.0")
  testImplementation("eu.codearte.catch-exception:catch-exception:2.0.0-beta-1")
}
