

plugins {
    id ("java")
    id("io.freefair.lombok") version "9.0.0"

}

group = "org.example"
version = "1.0-SNAPSHOT"
java{
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
    //J UNIT PARA PRUEBAS
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    //JPA API
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    //Logger
    implementation("org.slf4j:slf4j-simple:2.0.13")
    //IMPLEMENTACION DE JPA (Hibernate)
    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")
    //BASE DE DATOS EN MEMORIA H2
    runtimeOnly("com.h2database:h2:2.2.224")
}





