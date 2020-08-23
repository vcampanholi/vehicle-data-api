package br.com.vcampanholi.vehicledata.chassi.converter;

import br.com.vcampanholi.vehicledata.chassi.api.dto.ChassiRequest;
import br.com.vcampanholi.vehicledata.chassi.api.dto.ChassiResponse;
import br.com.vcampanholi.vehicledata.chassi.service.bo.ChassiBO;
import org.springframework.stereotype.Component;

@Component
public class ChassiConverter {

    public ChassiResponse convert(ChassiBO chassi) {
        return new ChassiResponse(chassi.getChassiNumber());
    }

    public ChassiBO convert(ChassiRequest request) {
        return ChassiBO.create()
                .unitsProduced(request.getUnitsProduced())
                .build();
    }

    public ChassiBO convert(String chassiNumber) {
        return ChassiBO.create()
                .chassiNumber(chassiNumber)
                .build();
    }

}
