package br.com.vcampanholi.vehicledata.chassi.api;

import br.com.vcampanholi.vehicledata.chassi.api.dto.ChassiRequest;
import br.com.vcampanholi.vehicledata.configuration.IntegrationTest;
import br.com.vcampanholi.vehicledata.exception.util.VehicleDataErrorCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

@IntegrationTest
public class ChassiControllerTest {

    @Autowired
    private WebTestClient webClientTest;

    @Test
    public void shouldGenerateChassiNumber() {
        ChassiRequest request = new ChassiRequest();

        webClientTest.post()
                .uri("/api/v1/chassi/generator")
                .syncBody(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.chassiNumber").isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(ints = {1693659, 88, 0})
    public void shouldGenerateChassiNumberWhenSendWithUnitsProduced(Integer unitsProduced) {
        ChassiRequest request = new ChassiRequest();
        request.setUnitsProduced(unitsProduced);

        webClientTest.post()
                .uri("/api/v1/chassi/generator")
                .syncBody(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.chassiNumber").isNotEmpty();
    }

    @Test
    public void shouldReturnBusinessExceptionWhenSendInvalidUnitsProduced() {
        ChassiRequest request = new ChassiRequest();
        request.setUnitsProduced(999999999);

        webClientTest.post()
                .uri("/api/v1/chassi/generator")
                .syncBody(request)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
                .expectBody()
                .jsonPath("$.code").isEqualTo(VehicleDataErrorCode.LIMIT_UNITS_PRODUCED.getCode())
                .jsonPath("$.msg").isNotEmpty();
    }

    @Test
    public void shouldValidateChassiNumberWithError() {
        webClientTest.get()
                .uri("/api/v1/chassi/validation?chassiNumber=9BVN26AA0CE6253399")
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
                .expectBody()
                .jsonPath("$.code").isEqualTo(VehicleDataErrorCode.CHASSI_NUMBER_MAX_SIZE.getCode())
                .jsonPath("$.msg").isNotEmpty();
    }

    @Test
    public void shouldValidateChassiNumberWithSuccess() {
        final String chassi = "9BVN26AA0CE625339";

        webClientTest.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/chassi/validation")
                        .queryParam("chassiNumber", chassi).build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$").isEqualTo("O chassi: " + chassi + " possui um formato válido.");
    }

}