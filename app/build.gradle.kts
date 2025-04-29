android {
  this.buildFeatures {
    this.buildConfig = true
  }

  this.defaultConfig {
    this.versionName = rootProject.ext["VERSION_NAME"].toString()
    this.versionCode = rootProject.ext["VERSION_CODE"].toString().toInt()
    this.buildConfigField("String", "EXFILAC_VERSION", "\"${rootProject.ext["VERSION_NAME"]}\"")
  }
}

dependencies {
  implementation("androidx.activity:activity:1.10.1")
  implementation("androidx.coordinatorlayout:coordinatorlayout:1.3.0")
  implementation("com.google.android.material:material:1.12.0")
}
