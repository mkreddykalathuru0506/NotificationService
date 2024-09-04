package com.letsGreen.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("LetsGreen API")
                .version("1.0")
                .description("API documentation for LetsGreen application"))
            .servers(List.of(
                new Server().url("http://localhost:2025").description("Local server")));
    }
}
