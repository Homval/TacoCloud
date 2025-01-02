package ru.example.tacocloud.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.info.Info;

import java.util.List;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Taco Cloud API",
                description = "API приложения Taco Cloud",
                version = "1.0.0",
                contact = @Contact(
                        name = "Valeriy",
                        email = "my-email@mail.ru",
                        url = "https://my_site.org"
                )
        )
)
@SecurityScheme(
        name = "sessionId",
        in = SecuritySchemeIn.COOKIE,
        type = SecuritySchemeType.APIKEY,
        paramName = "JSESSIONID"
)
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(List.of(new Server().url("http://localhost:8080")));
    }
}
