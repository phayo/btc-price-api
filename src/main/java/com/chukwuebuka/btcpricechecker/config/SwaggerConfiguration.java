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
                                .title("WebFactory BTC price api")
                                .description("Bitcoin price api")
                                .version("1.0.0-SNAPSHOT").contact(new Contact().name("Chukwuebuka Anazodo").
                                url("https://developer.webfactory.mk").email("chukwuebuka@webfactory.mk"))
                                .license(
                                        new License().name("WEBFACTORY LICENSE").url("https://license.webfactory.mk")
                                        )
                     );
    }
}
