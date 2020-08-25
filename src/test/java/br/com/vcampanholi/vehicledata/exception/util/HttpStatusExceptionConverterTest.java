package br.com.vcampanholi.vehicledata.exception.util;

import br.com.vcampanholi.vehicledata.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HttpStatusExceptionConverterTest {

    private final HttpStatusExceptionConverter httpStatusConverter = new HttpStatusExceptionConverter();

    @Test
    public void shouldReturn422WhenIsABusinessException() {
        final int code = httpStatusConverter.convert(new BusinessException(null));
        assertEquals(422, code);
    }

    @Test
    public void shouldReturn403WhenIsAForbiddenException() {
        final int code = httpStatusConverter.convert(new ForbiddenException(null));
        assertEquals(403, code);
    }

    @Test
    public void shouldReturn404WhenIsANotFoundException() {
        final int code = httpStatusConverter.convert(new NotFoundException(null));
        assertEquals(404, code);
    }

    @Test
    public void shouldReturn500WhenIsATechnicalException() {
        final int code = httpStatusConverter.convert(new TechnicalException(null));
        assertEquals(500, code);
    }

    @Test
    public void shouldReturn400WhenIsAnIllegalArgumentException() {
        final int code = httpStatusConverter.convert(new IllegalArgumentException(""));
        assertEquals(400, code);
    }

    @Test
    public void shouldReturn400WhenIsABadRequestException() {
        final int code = httpStatusConverter.convert(new BadRequestException(null));
        assertEquals(400, code);
    }

    @Test
    public void shouldReturn500WhenExceptionIsNotMapped() {
        final int code = httpStatusConverter.convert(new NullPointerException(""));
        assertEquals(500, code);
    }

}