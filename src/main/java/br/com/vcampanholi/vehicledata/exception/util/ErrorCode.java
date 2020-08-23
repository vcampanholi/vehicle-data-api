package br.com.vcampanholi.vehicledata.exception.util;

import java.io.Serializable;

public class ErrorCode implements Serializable {

    private String code;
    private transient Object[] parameters;

    public ErrorCode(final String code) {
        this.code = code;
    }

    public ErrorCode(final String code, final Object[] parameters) {
        this.code = code;
        this.parameters = parameters;
    }

    public String getCode() {
        return code;
    }

    public Object[] getParameters() {
        return parameters;
    }
}
