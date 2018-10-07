buildscript {
  repositories {
    jcenter()
    google()
    maven { url = uri("http://dl.bintray.com/arturbosch/code-analysis") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }

  }
  dependencies {
    classpath("com.android.tools.build:gradle:3.2.0")
    classpath(kotlin("gradle-plugin", version = Deps.KOTLIN_VERSION))
  }
}

allprojects {
  repositories {
    jcenter()
    google()
    jcenter()
    maven { url = uri("http://dl.bintray.com/arturbosch/code-analysis") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { url = uri("https://jitpack.io") }
  }
}
