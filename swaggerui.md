# Setting up Swagger UI with Java Spring Boot is a great way to document your RESTful APIs. Here's a step-by-step guide to get you started

1. **Add Dependencies**: First, you need to add the necessary dependencies to your `pom.xml` file if you're using Maven:

   ```xml
   <dependency>
       <groupId>io.springfox</groupId>
       <artifactId>springfox-boot-starter</artifactId>
       <version>3.0.0</version>
   </dependency>
   ```

2. **Create a Swagger Configuration Class**: Create a new configuration class to set up Swagger:

   ```java
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   import springfox.documentation.builders.PathSelectors;
   import springfox.documentation.builders.RequestHandlerSelectors;
   import springfox.documentation.spi.DocumentationType;
   import springfox.documentation.spring.web.plugins.Docket;
   import springfox.documentation.swagger2.annotations.EnableSwagger2;

   @Configuration
   @EnableSwagger2
   public class SwaggerConfig {
       @Bean
       public Docket api() {
           return new Docket(DocumentationType.SWAGGER_2)
                   .select()
                   .apis(RequestHandlerSelectors.any())
                   .paths(PathSelectors.any())
                   .build();
       }
   }
   ```

3. **Run Your Application**: Start your Spring Boot application. Swagger UI should be accessible at `http://localhost:8080/swagger-ui.html`.

4. **Access Swagger UI**: Open your browser and navigate to `http://localhost:8080/swagger-ui.html`. You should see the Swagger UI interface where you can interact with your API endpoints.

Would you like more details on any of these steps or additional customization options?
[43dcd9a7-70db-4a1f-b0ae-981daa162054](https://github.com/bovenson/notes/tree/387074588c50973b6fb8645f859ae9ca29b4df4c/Architecture%2FFramework%2FSwagger%2FSwagger.md?citationMarker=43dcd9a7-70db-4a1f-b0ae-981daa162054 "1")[43dcd9a7-70db-4a1f-b0ae-981daa162054](https://github.com/arthurmz/sca-monitoramento-barragens/tree/430d82245085c8c7bee3d67457cc7c1db3be7aaa/src%2Fmain%2Fjava%2Fcom%2Fsca%2Fmonitoramento_barragens%2Fconfig%2FSwaggerConfig.java?citationMarker=43dcd9a7-70db-4a1f-b0ae-981daa162054 "2")[43dcd9a7-70db-4a1f-b0ae-981daa162054](https://github.com/lakermann/social-playlist/tree/63e94d8874f4153c8883cc5e53d668a8ffcc2cbe/docs%2Fsrc%2Fbackend%2Ffirst-spring-boot-application.md?citationMarker=43dcd9a7-70db-4a1f-b0ae-981daa162054 "3")
