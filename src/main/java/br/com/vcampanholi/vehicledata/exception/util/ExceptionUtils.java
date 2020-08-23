package br.com.vcampanholi.vehicledata.exception.util;

import br.com.vcampanholi.vehicledata.exception.*;

public final class ExceptionUtils extends ContractsException {
    public ExceptionUtils(ErrorCode error) {
        super(error);
    }

    public static final BusinessException asBusinessException(final VehicleDataErrorCode errorCode) {
        throw businessException(errorCode);
    }

    public static final BusinessException businessException(final VehicleDataErrorCode errorCode) {
        return new BusinessException(errorCode.withoutParams());
    }

    public static final void asBusinessExceptionWithFormattedMessage(final VehicleDataErrorCode errorCode, final String... params) {
        throw businessExceptionWithFormattedMessage(errorCode, params);
    }

    public static final BusinessException businessExceptionWithFormattedMessage(final VehicleDataErrorCode errorCode, final String... params) {
        return new BusinessException(errorCode.withParams(params));
    }

    public static final void asNotFoundException(final VehicleDataErrorCode errorCode) {
        throw notFoundException(errorCode);
    }

    public static final NotFoundException notFoundException(final VehicleDataErrorCode errorCode) {
        return new NotFoundException(errorCode.withoutParams());
    }

    public static final void asNotFoundExceptionWithFormattedMessage(final VehicleDataErrorCode errorCode, final String... params) {
        throw notFoundExceptionWithFormattedMessage(errorCode, params);
    }

    public static final NotFoundException notFoundExceptionWithFormattedMessage(final VehicleDataErrorCode errorCode, final String... params) {
        return new NotFoundException(errorCode.withParams(params));
    }

    public static final TechnicalException asTechnicalException(final VehicleDataErrorCode errorCode) {
        throw technicalException(errorCode);
    }

    public static final TechnicalException technicalException(final VehicleDataErrorCode errorCode) {
        return new TechnicalException(errorCode.withoutParams());
    }

    public static final void astechnicalExceptionWithFormattedMessage(final VehicleDataErrorCode errorCode, final String... params) {
        throw technicalExceptionWithFormattedMessage(errorCode, params);
    }

    public static final TechnicalException technicalExceptionWithFormattedMessage(final VehicleDataErrorCode errorCode, final String... params) {
        return new TechnicalException(errorCode.withParams(params));
    }

    public static final ForbiddenException asForbiddenException(final VehicleDataErrorCode errorCode) {
        throw forbiddenException(errorCode);
    }

    public static final ForbiddenException forbiddenException(final VehicleDataErrorCode errorCode) {
        return new ForbiddenException(errorCode.withoutParams());
    }

    public static final UnauthorizedException asUnauthorizedException(final VehicleDataErrorCode errorCode) {
        throw unauthorizedException(errorCode);
    }

    public static final UnauthorizedException unauthorizedException(final VehicleDataErrorCode errorCode) {
        return new UnauthorizedException(errorCode.withoutParams());
    }

}
