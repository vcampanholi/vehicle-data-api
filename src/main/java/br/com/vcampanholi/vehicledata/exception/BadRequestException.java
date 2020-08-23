package br.com.vcampanholi.vehicledata.exception;

import br.com.vcampanholi.vehicledata.exception.util.ErrorCode;

public class BadRequestException extends ContractsException{

    public BadRequestException(final ErrorCode error) {
        super(error);
    }

}
