dependencies {
    api(project(":application"))

    testImplementation(testFixtures(project(":domain")))
    testImplementation(libs.bundles.tests.all)
    testImplementation(libs.bundles.cucumber.all)
}
