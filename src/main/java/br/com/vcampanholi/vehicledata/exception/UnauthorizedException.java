package br.com.vcampanholi.vehicledata.exception;

import br.com.vcampanholi.vehicledata.exception.util.ErrorCode;

public class UnauthorizedException extends ContractsException{

    public UnauthorizedException(final ErrorCode error) {
        super(error);
    }
}
