package br.com.vcampanholi.vehicledata.exception.util;

public enum VehicleDataErrorCode {

    LIMIT_UNITS_PRODUCED("VEI-CHA-01"),
    CHASSI_NUMBER_NOT_FOUND("VEI-CHA-02"),
    CHASSI_NUMBER_MAX_SIZE("VEI-CHA-03"),
    CHASSI_NUMBER_FIRST_CHARACTER("VEI-CHA-04"),
    CHASSI_NUMBER_EMPTY_SPACES("VEI-CHA-05"),
    CHASSI_NUMBER_REPETIONS_FROM_FOURTH_DIGIT("VEI-CHA-06"),
    CHASSI_NUMBER_ChARACTERES_NOT_ALLOWED("VEI-CHA-07"),
    CHASSI_NUMBER_VALIDATE_LAST_SIX_CHARACTERS("VEI-CHA-08"),

    INFRA_ERROR_CODE("VEI-INF-01");

    private String code;

    VehicleDataErrorCode(final String code) {
        this.code = code;
    }

    public ErrorCode withoutParams() {
        return withParams();
    }

    public ErrorCode withParams(String... parameters) {
        return new ErrorCode(this.code, parameters);
    }

    public String getCode() {
        return code;
    }
}
