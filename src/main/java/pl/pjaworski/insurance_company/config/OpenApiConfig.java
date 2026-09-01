package pl.pjaworski.examplebackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI exampleBackendOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Example Backend Service API")
                        .description("API documentation for the Example Backend Service")
                        .version("0.0.1-SNAPSHOT"));
    }
}
