plugins {
    java
    application
    id("org.springframework.boot") version "2.1.4.RELEASE"
}

apply(plugin = "io.spring.dependency-management")

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

application {
    group = "com.npaw.techtest"
    version = "0.0.1-SNAPSHOT"
    mainClassName = "com.npaw.techtest.plugindataservice.PluginDataServiceApplication"
}

repositories {
    mavenCentral()
}

ext {
    // IMPORTANT: Override JUnit BOM from spring dependency management
    // See: https://stackoverflow.com/questions/54598484/gradle-5-junit-bom-and-spring-boot-incorrect-versions
    set("junit-jupiter.version", "5.4.0")
}

dependencies {

    implementation("org.springframework.boot", "spring-boot-starter-web")
    annotationProcessor("org.springframework.boot", "spring-boot-configuration-processor")
    compileOnly("org.projectlombok","lombok")
    annotationProcessor("org.projectlombok","lombok")
    
    // metrics and monitoring
    implementation("org.springframework.boot", "spring-boot-devtools")
    implementation("org.springframework.boot", "spring-boot-starter-actuator")


    // testing
    testImplementation("org.springframework.boot", "spring-boot-starter-test") {
        // Exclude JUnit 4 from starter-test, because it will be used JUnit 5
        exclude(group = "junit")
        exclude(group = "org.mockito")
        exclude(group = "org.hamcrest")
    }
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.4.0")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.4.0")
}

tasks {
    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform()
    }
}