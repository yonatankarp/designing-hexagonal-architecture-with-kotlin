dependencies {
    api(project(":application"))

    implementation(libs.bundles.jackson.all)

    testImplementation(libs.bundles.tests.all)
}
