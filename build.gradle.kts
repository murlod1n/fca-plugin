plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.intellij.platform") version "2.2.1"
}

group = "com.murlodin"
version = "1.0.0"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

intellijPlatform {

    pluginConfiguration {
        name = "FCA"
        id="com.murlodin.fca-plugin"

    }

    publishing {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
    signing {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }
}

dependencies {
    intellijPlatform {
        local("/Applications/Android Studio.app/Contents")
    }
}

