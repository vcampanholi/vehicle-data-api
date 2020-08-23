package br.com.vcampanholi.vehicledata.exception;

import br.com.vcampanholi.vehicledata.exception.util.ErrorCode;

public class NotFoundException extends ContractsException{

    public NotFoundException(final ErrorCode error) {
        super(error);
    }

}
