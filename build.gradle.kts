
plugins {
    // Active le langage Java
    java

    // Permet de créer une application Spring Boot
    id("org.springframework.boot") version "4.2.0"

    // Gère les versions des dépendances Spring
    id("io.spring.dependency-management") version "1.1.7"
}

// Identité de notre projet
group = "com.AppliBanque"
version = "0.0.1-SNAPSHOT"

// Utilise Java 25
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

// Indique où télécharger les bibliothèques
repositories {
    mavenCentral()
}

dependencies {
    // Fonctionnalités de base de Spring Boot
    implementation("org.springframework.boot:spring-boot-starter")

    // Permet de créer une API REST
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Affiche les journaux de l'application
    implementation("org.springframework.boot:spring-boot-starter-logging")

    // Vérifie les données reçues (@NotBlank, @Size...)
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Génère la documentation Swagger / OpenAPI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.2.1")

    // Génère les identifiants uniques ULID
    implementation("com.github.f4b6a3:ulid-creator:5.2.3")

    // Ajout dépendances Spring JDBC et PostgreSQL
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    runtimeOnly("org.postgresql:postgresql")


    // Bibliothèques pour les tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Permet d'exécuter les tests JUnit 5
tasks.withType<Test> {
    useJUnitPlatform()
}
