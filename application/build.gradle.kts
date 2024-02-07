dependencies {
    api(project(":domain"))

    testImplementation(testFixtures(project(":domain")))
    testImplementation(libs.bundles.tests.all)
    testImplementation(libs.bundles.cucumber.all)
}
