# Getting Started

Documentation from <https://springdoc.org/>

For the integration between spring-boot and swagger-ui, add the library to the list of your project dependencies (No additional configuration is needed)

```xml
   <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.6.0</version>
   </dependency>
```

This will automatically deploy swagger-ui to a spring-boot application:

Documentation will be available in HTML format, using the official swagger-ui jars

The Swagger UI page will then be available at 

`http://localhost:8080/swagger-ui.html` and the OpenAPI description will be available at the following url for json format: `http://localhost:8080/v3/api-docs`
