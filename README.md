# plugin-data-service

Simple component that allows to handle requests of NPAW plugins.

This component exposes a REST HTTP API.

## Getting started

### Clone Repository

Use Git or checkout with SVN using the following web URL:
```
git@github.com:ajdafonte/plugin-data-service.git
```

### Build and run component

Execute the following gradle task:
```
gradlew.bat build
```

### Running the tests

Execute the following gradle task:
```
gradlew.bat test
```

### Running the component
```
gradlew.bat bootRun
```

### Import into IDE

This is a Gradle project (build.gralde.kts). Here are some instructions on how to import a Gradle project in some of the most popular IDEs:
- IntelliJ - https://www.jetbrains.com/help/idea/gradle.html#gradle_import
- Eclipse - https://www.vogella.com/tutorials/EclipseGradle/article.html#import-an-existing-gradle-project

## Technical Comments

In this section will be described some technical details of this project.

### Stack
  
Here's a short summary of the tech stack used in the development of this component.
- Java 8, Spring Boot, Gradle
- Main dependencies: 
    - spring-boot-starter-web, lombok, swagger, junit5, mockito, hamcrest  

### Component Configuration

In file `application.yml` some properties were defined that allows to:
 - Define component listening port - `server.port`
 - Define logging level - `logging.level`
 - Define threadpool configuration - `pluginDataService.bizzThreadPoolConfig`
 - Define clients configurations - `pluginDataService.clientsConfigs`  

### Component Functionality

#### Available endpoints

- `GET /pluginData` - Retrieve a plugin data information considering some query parameters.
    - Input data: accountCode, targetDevice and pluginVersion
        - Example of request URL: ``` http://localhost:80/pluginData?accountCode=clientA&targetDevice=XBox&pluginVersion=3.3.1 ```
        - Example of Curl: ``` curl -X GET "http://localhost/pluginData?accountCode=clientA&targetDevice=XBox&pluginVersion=3.3.1" -H "accept: application/xml" ```
    - Output data: hostName, pingTime, viewId
        - Example of response body: ``` <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <q><h>clusterA.com</h><pt>10</pt><c>46ea39d3-f401-4961-aa01-a3046a810312</c></q> ```

For more details about this endpoint open http://localhost:80/swagger-ui.html.

#### JMX Operations

The following JMX operations were implemented in order to manage the update, at runtime, of some of the clients configurations fields.

- `getConfigurationForClient` - Returns configuration for a certain client.
- `updateClientPluginConfiguration` - For a certain client and respective target device, updates the plugin version and/or ping time.
- `updateClientPluginHostConfiguration` - For a certain client, target device and host name, updates the host load percentage.

All of these operations returns a String with the result of the operation (an object or an error message).

- Instructions on how to access those operations
    - Open [JConsole](https://docs.oracle.com/javase/7/docs/technotes/guides/management/jconsole.html) application
    - Connect to plugin-data-service component
    - Go to MBeans tab
    - Search for node `com.npaw.techtest.plugindataservice:PluginDataConfig`
    - Open 'Operations' node 
 

### Additional Remarks

- In folder `misc`, there's a file named `plugin-data-service-performance-test.jmx` that contain a [JMeter](https://jmeter.apache.org/) test plan in order 
to perform some performance validations of the available endpoints.