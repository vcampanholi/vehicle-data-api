package br.com.vcampanholi.vehicledata.exception;

import br.com.vcampanholi.vehicledata.exception.util.ErrorCode;
import org.apache.commons.lang3.ArrayUtils;

public abstract class ContractsException extends RuntimeException {

    private final ErrorCode errorCode;

    private static final String ERROR_CODE_NOT_FOUND = "Error code not found.";

    public ContractsException(final ErrorCode error) {
        super(error != null ? error.getCode() : ERROR_CODE_NOT_FOUND);
        this.errorCode = error;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {

        String suffix = "";
        if (errorCode != null && ArrayUtils.isNotEmpty(errorCode.getParameters())) {
            suffix += " - ";
            for (Object parameter : errorCode.getParameters()) {
                suffix += parameter.toString();
            }
        }

        return super.getMessage() + suffix;
    }


}
