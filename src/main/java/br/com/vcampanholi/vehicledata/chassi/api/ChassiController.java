package br.com.vcampanholi.vehicledata.chassi.api;

import br.com.vcampanholi.vehicledata.chassi.api.dto.ChassiRequest;
import br.com.vcampanholi.vehicledata.chassi.api.dto.ChassiResponse;
import br.com.vcampanholi.vehicledata.chassi.converter.ChassiConverter;
import br.com.vcampanholi.vehicledata.chassi.service.ChassiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/vehicle/chassi")
public class ChassiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChassiController.class);

    private final ChassiService chassiService;
    private final ChassiConverter converter;

    public ChassiController(ChassiService chassiService, ChassiConverter converter) {
        this.chassiService = chassiService;
        this.converter = converter;
    }

    @PostMapping()
    public Mono<ChassiResponse> generateChassiNumber(@RequestBody ChassiRequest request) {
        return Mono.just(converter.convert(request))
                .flatMap(chassiService::generateChassiNumber)
                .map(converter::convert)
                .doOnNext(chassi -> LOGGER.info("Chassi: {} gerado com sucesso", chassi.getChassiNumber()))
                .doOnRequest(log -> LOGGER.info("Gerando número de chassi com os dados: {}", request));
    }

    @GetMapping(path = "/validation")
    public Mono<String> validateChassi(@RequestParam(value = "chassiNumber") final String chassiNumber) {
        return Mono.just(converter.convert(chassiNumber))
                .flatMap(chassiService::validateChassiNumber)
                .doOnRequest(log -> LOGGER.info("Validadando chassi: {}", chassiNumber));
    }

}
