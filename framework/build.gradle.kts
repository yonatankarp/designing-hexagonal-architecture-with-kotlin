dependencies {
    api(project(":application"))

    testImplementation(libs.bundles.tests.all)
    implementation(libs.bundles.cucumber.all)
}
