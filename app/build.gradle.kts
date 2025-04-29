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

}
