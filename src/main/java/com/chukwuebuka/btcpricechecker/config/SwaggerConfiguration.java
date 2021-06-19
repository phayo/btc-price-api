package com.chukwuebuka.btcpricechecker.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    public @Bean
    OpenAPI noteAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Lunatech Movie Service")
                                .description("Movie service based off of IMDB data")
                                .version("1.0.0-SNAPSHOT").contact(new Contact().name("Chukwuebuka Anazodo").
                                url("https://developer.lunatech.nl").email("chukwuebuka@lunatech.nl"))
                                .license(
                                        new License().name("LUNATECH LICENSE").url("https://license.lunatch.nl")
                                        )
                     );
    }
}
