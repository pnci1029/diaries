plugins {
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    kotlin("plugin.jpa") version "1.8.21"
    kotlin("kapt") version "1.7.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
val queryDslVersion = "5.0.0"
val querydslDir = "src/main/generated"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // QueryDsl
    kapt("com.querydsl:querydsl-apt:$queryDslVersion:jakarta")
    implementation("com.querydsl:querydsl-jpa:$queryDslVersion:jakarta")

    // Swagger
    implementation("io.springfox:springfox-swagger-ui:3.0.0")
    implementation("io.springfox:springfox-boot-starter:3.0.0")


    // MapStruct
    implementation("org.mapstruct:mapstruct:1.5.2.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.2.Final")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets {
    getByName("main").java.srcDirs(querydslDir, "src/main/kotlin")
}