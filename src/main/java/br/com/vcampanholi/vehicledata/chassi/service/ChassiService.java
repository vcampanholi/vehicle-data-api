package br.com.vcampanholi.vehicledata.chassi.service;

import br.com.vcampanholi.vehicledata.chassi.service.bo.ChassiBO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Random;

import static org.apache.commons.lang3.StringUtils.rightPad;
    /*Codificação
    Interpretação dos dígitos
    Dígitos: 1 a 3
    9-Região Geográfica - América do Sul
    B-País - Brasil
    D-Fabricante - Fiat Automóveis S.A.
    Dígitos: 4 a 6
    Modelo do Veículo (3 caracteres) 171-Palio
    Dígitos 7 a 9:
    O37-Projeto (3 dígitos) Pálio 1.0 EFI
    Dígito 10:
    4-Ano/modelo de fabricação 2004/2004
    Dígito 11:
    2-Linha/Unidade de montagem Betim ou Sete Lagos, MG
    Dígitos 12 a 17:
    Números de série 477.382 Unidades produzidas*/

@Service
public class ChassiService {

    private static final String GEOGRAPHICAL_AREA = "9";//America do Sul
    private static final String COUNTRY = "B"; //Brasil
    private static final String BRAND = "V"; //Marca do veiculo - Volvo do Brasil
    private static final String VEHICLE_DESCRIPITIVE_SECTION = "N26AA0";//Seção Descritiva do Veículo (VDS)
    private static final String YEAR = "C";//Ano 2012
    private static final String FACTORY_LOCATION = "E";//Local da fábrica
    private static final Integer LIMIT_VALUE = 800000;

    public Mono<ChassiBO> generateChassiNumber(final ChassiBO chassi) {
        return Mono.just(ChassiBO.create()
                .chassiNumber(buildChassiNumber(chassi))
                .build());
    }

    private String buildChassiNumber(ChassiBO chassiBO) {
        String chassiNumber = "";
        return chassiNumber
                .concat(GEOGRAPHICAL_AREA)
                .concat(COUNTRY)
                .concat(BRAND)
                .concat(VEHICLE_DESCRIPITIVE_SECTION)
                .concat(YEAR)
                .concat(FACTORY_LOCATION)
                .concat(getSequencialNumber(chassiBO.getUnitsProduced()));
    }

    private String getSequencialNumber(Integer limitValue) {
        if (limitValue == null || limitValue == 0) limitValue = LIMIT_VALUE;

        Random random = new Random();
        int sequentialNumber = random.nextInt(limitValue);
        String sequencial = Long.toString(sequentialNumber);

        if (sequencial.length() > 6) {
            return sequencial.substring(0, 6);
        } else if (sequencial.length() < 6) {
            return rightPad(sequencial, 6, "1");
        }
        return Long.toString(sequentialNumber);
    }

    public Mono<String> validateChassiNumber(ChassiBO chassi) {
        return Mono.just(chassi.getChassiNumber())
                .map(number -> "O chassi: " + number + " possui um formato válido.");
    }
}