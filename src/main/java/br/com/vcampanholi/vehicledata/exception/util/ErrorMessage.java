package br.com.vcampanholi.vehicledata.exception.util;

public class ErrorMessage {

    private String code;

    private String msg;

    public ErrorMessage(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
