plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "org.buddy"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

tasks.register("bootRunDev") {
    group = "application"
    description = "Runs the Spring Boot application with the dev profile"

    doFirst {
        val env = System.getenv()
        val databaseUri = env.getOrDefault("DATABASE_URI", "no-database-specified")
        val port = env.getOrDefault("PORT", "8080")

        tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun>().configureEach {
            systemProperty("spring.profiles.active", "dev")
            environment("DATABASE_URI", databaseUri)
            environment("PORT", port)
        }
    }

    finalizedBy("bootRun")
}


tasks.register("bootRunProd") {
    group = "application"
    description = "Runs the Spring Boot application with the prod profile"
    doFirst {
        tasks.bootRun.configure {
            systemProperty("spring.profiles.active", "prod")
        }
    }
    finalizedBy("bootRun")
}

dependencies {
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.3.0")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.6")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.mongodb:mongodb-driver-sync:5.1.0")
    implementation("com.google.firebase:firebase-admin:9.3.0")
    compileOnly("org.projectlombok:lombok:1.18.32")
    developmentOnly("org.springframework.boot:spring-boot-devtools:3.3.0")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.2.6")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.6")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("spring.profiles.active", "test")
}
