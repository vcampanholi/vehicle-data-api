package br.com.vcampanholi.vehicledata.configuration;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.test.web.reactive.server.WebTestClient;

@TestConfiguration
public class TestConfig {

    @Bean
    @Scope("prototype")
    public WireMock wireMock(@Value("${wiremock.server.port}") Integer port) {
        return WireMock.create()
                .port(port)
                .build();
    }

    @Bean
    @Scope("prototype")
    public WebTestClient webClientTest(ApplicationContext context) {
        return WebTestClient.bindToApplicationContext(context)
                .build()
                ;
    }

}
