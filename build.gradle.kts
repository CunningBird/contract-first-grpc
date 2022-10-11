import com.google.protobuf.gradle.*

plugins {
    id("com.google.protobuf") version "0.8.19"
    id("java")
    id("maven-publish")
}

repositories {
    mavenCentral()
}

dependencies {
    // Build context
    compileOnly("io.grpc:grpc-stub:1.49.2")
    compileOnly("io.grpc:grpc-protobuf:1.49.2")
    compileOnly("com.google.protobuf:protobuf-java:3.21.7")

    // Test context
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("io.grpc:grpc-stub:1.49.2")
    testImplementation("io.grpc:grpc-protobuf:1.49.2")
    testImplementation("io.grpc:grpc-netty:1.49.2")
    testImplementation("com.google.protobuf:protobuf-java:3.21.7")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

sourceSets {
    test {
        java {
            srcDir("${buildDir}/generated/source/proto")
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.6.1"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.15.1"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc")
            }
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/cunningbird-lab/contract-first-grpc")
            credentials {
                username = System.getProperty("publishRegistryUsername")
                password = System.getProperty("publishRegistryPassword")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.cunningbird.templates"
            artifactId = "contract-first-grpc"
            version = "1.0.0"
            from(components["java"])
        }
    }
}