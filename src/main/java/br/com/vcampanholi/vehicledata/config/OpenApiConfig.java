package br.com.vcampanholi.vehicledata.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${VERSION:latest}") final String version) {
        return new OpenAPI()
                .components(new Components())
                .info(getInfo(version));
    }

    private Info getInfo(String version) {
        return new Info()
                .contact(getContact())
                .title("Vehicle Data")
                .description("Vehicle Data API")
                .version(version);
    }

    private Contact getContact() {
        return new Contact().name("Vanderson Campanholi")
                .email("vando.zc@gmail.com");
    }

}
