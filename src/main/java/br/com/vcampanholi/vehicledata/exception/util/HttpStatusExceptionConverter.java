package br.com.vcampanholi.vehicledata.exception.util;

import br.com.vcampanholi.vehicledata.exception.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionException;

@Component
public class HttpStatusExceptionConverter {

    /**
     * Convert {@link Throwable} into a {@link Integer} representing HTTP Status Code.
     *
     * @param throwable exception to convert
     * @return {@link Integer}
     */
    public Integer convert(final Throwable throwable) {
        if (throwable instanceof BusinessException) return 422;
        else if (throwable instanceof ForbiddenException) return 403;
        else if (throwable instanceof UnauthorizedException) return 401;
        else if (throwable instanceof NotFoundException) return 404;
        else if (throwable instanceof TechnicalException) return 500;
        else if (throwable instanceof IllegalArgumentException) return 400;
        else if (throwable instanceof BadRequestException) return 400;
        else if (throwable instanceof CompletionException) return this.convert(throwable.getCause());
        else return 500;
    }
}