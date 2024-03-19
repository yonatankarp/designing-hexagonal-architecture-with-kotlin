plugins {
    kotlin("plugin.jpa") version "1.9.22"
}

dependencies {
    api(project(":application"))

    implementation(libs.bundles.jackson.all)
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("org.jetbrains.kotlin:kotlin-maven-noarg")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("org.eclipse.persistence:org.eclipse.persistence.jpa:4.0.2")

    testImplementation(libs.bundles.tests.all)
}
