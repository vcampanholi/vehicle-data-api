package br.com.vcampanholi.vehicledata.exception;

import br.com.vcampanholi.vehicledata.exception.util.ErrorCode;

public class ForbiddenException extends ContractsException{

    public ForbiddenException(final ErrorCode error) {
        super(error);
    }
}
