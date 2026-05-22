plugins {
    java
    `maven-publish`
}

group   = "fr.blueprynt"
version = "1.0.0"

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    // Paper API — provided at runtime by the server, never bundled
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(21)
}

// Suppress missing Javadoc warnings for stub methods
tasks.withType<Javadoc>().configureEach {
    (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
}

publishing {
    publications {
        create<MavenPublication>("api") {
            groupId    = "fr.blueprynt"
            artifactId = "blueprynt-api"
            version    = project.version.toString()

            from(components["java"])

            pom {
                name.set("Blueprynt API")
                description.set("Public compile-time API for the Blueprynt visual scripting engine.")
                url.set("https://github.com/AnaNazzDev/Blueprynt-API")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("AnaNazzDev")
                        name.set("AnaNazzDev")
                        url.set("https://github.com/AnaNazzDev")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/AnaNazzDev/Blueprynt-API.git")
                    developerConnection.set("scm:git:ssh://github.com/AnaNazzDev/Blueprynt-API.git")
                    url.set("https://github.com/AnaNazzDev/Blueprynt-API")
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url  = uri("https://maven.pkg.github.com/AnaNazzDev/Blueprynt-API")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
