package com.vedha.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Chat API", version = "1.0", description = "Documentation Chat API",
        contact = @Contact(name = "Vedha", email = "sarath1242000@gmail.com"))
)
public class OpenApiConfig {
}
