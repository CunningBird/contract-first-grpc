pluginManagement {
    repositories {
        mavenLocal()
        maven("https://plugins.gradle.org/m2/")
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.google.protobuf") {
                useModule("com.google.protobuf:protobuf-gradle-plugin:${requested.version}")
            }
        }
    }
}

rootProject.name = "contract-first-grpc"

// TODO mock client and server in test
// TODO test example
// TODO CI/CD