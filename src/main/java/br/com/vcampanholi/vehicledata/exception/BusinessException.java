package br.com.vcampanholi.vehicledata.exception;

import br.com.vcampanholi.vehicledata.exception.util.ErrorCode;

public class BusinessException extends ContractsException {

    public BusinessException(final ErrorCode error) {
        super(error);
    }
}
