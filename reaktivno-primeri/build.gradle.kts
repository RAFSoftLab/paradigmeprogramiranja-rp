plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "raf.rs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation(platform("io.projectreactor:reactor-bom:2024.0.0"))
    implementation("io.projectreactor:reactor-core")

    // novi dipendensi

    // Spring Data R2DBC
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")

    // H2 Database
    runtimeOnly("com.h2database:h2")

    // R2DBC driver for H2
    runtimeOnly("io.r2dbc:r2dbc-h2")

    // WebFlux
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    testImplementation("io.projectreactor:reactor-test")
    testImplementation(platform("org.junit:junit-bom:6.0.0"))

}

tasks.test {
    useJUnitPlatform()
}