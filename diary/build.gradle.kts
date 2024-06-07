plugins {
//    java
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    kotlin("plugin.jpa") version "1.8.21"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    kotlin("kapt") version "1.5.0"
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
//    코틀린에서 롬복 불가
//    implementation("org.projectlombok:lombok")
//    annotationProcessor("org.projectlombok:lombok")

    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // QueryDsl
    kapt("com.querydsl:querydsl-apt:$queryDslVersion:jakarta")
    kapt("com.querydsl:querydsl-jpa")
    implementation("com.querydsl:querydsl-jpa:5.0.0")
    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")

    // Swagger
    implementation("io.springfox:springfox-swagger-ui:3.0.0")
    implementation("io.springfox:springfox-boot-starter:3.0.0")

    implementation("org.hibernate:hibernate-core:5.6.4.Final")

    implementation("org.mapstruct:mapstruct:1.5.2.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.2.Final")
}


tasks.withType<Test> {
    useJUnitPlatform()
}


// src/main/ java or kotlin 문제 처리
sourceSets {
    getByName("main").java.srcDirs(querydslDir, "src/main/kotlin")
}