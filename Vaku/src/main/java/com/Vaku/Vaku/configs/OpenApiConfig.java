package com.Vaku.Vaku.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Vaku", version = "1.0", description = "Documentation for endpoints in Vaku")
)
public class OpenApiConfig {
}
