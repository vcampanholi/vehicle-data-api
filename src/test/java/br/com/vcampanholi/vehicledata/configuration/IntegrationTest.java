package br.com.vcampanholi.vehicledata.configuration;

import br.com.vcampanholi.vehicledata.VehicleDataApplication;
import com.github.tomakehurst.wiremock.core.Options;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ActiveProfiles("test")
@SpringBootTest(classes = {VehicleDataApplication.class, TestConfig.class}, properties = "spring.cache.type=NONE")
@AutoConfigureWireMock(port = Options.DYNAMIC_PORT)
@AutoConfigureWebTestClient(timeout = "10000")
@ExtendWith(SpringExtension.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface IntegrationTest {

}
