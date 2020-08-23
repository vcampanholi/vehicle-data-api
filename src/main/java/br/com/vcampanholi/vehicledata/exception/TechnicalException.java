package br.com.vcampanholi.vehicledata.exception;

import br.com.vcampanholi.vehicledata.exception.util.ErrorCode;

public class TechnicalException extends ContractsException {

    public TechnicalException(final ErrorCode error) {
        super(error);
    }
}
