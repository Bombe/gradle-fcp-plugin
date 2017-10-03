# gradle-fcp-plugin

This is a plugin for [Gradle](https://gradle.org/) that allows a build script to send messages to [Freenet](https://freenetproject.org/) plugins using FCP.

## Usage

1. Install it in your local Maven repository.

```
./gradlew publishPluginPublicationToMavenLocal
```

2. Include it in your `buildscript` section.

```
buildscript {
  repositories {
    // at the moment you have to install it locally.
    mavenLocal()
  }
  dependencies {
    classpath group: 'net.pterodactylus', name: 'gradle-fcp-plugin', version: '0.1-SNAPSHOT'
  }
}
```

3. Configure your FCP connection.

```
fcp {
  host = "my-freenet-host"
  port = "12345"
}
```

If your Freenet node is running on `localhost` and uses the default FCP port `9481`, you can skip this because those values are the default.

4. Send a message.

```
pluginFcp {
  plugin = "my.super.cool.Plugin"
  messages = "GreatMessage"
  parameters = ["howMuchCooler": "at least 20%", "howLoud": "11"]
}
```
