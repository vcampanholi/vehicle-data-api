package br.com.vcampanholi.vehicledata.config;

import br.com.vcampanholi.vehicledata.exception.ContractsException;
import br.com.vcampanholi.vehicledata.exception.util.*;
import io.vavr.Predicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Configuration
public class WebFluxErrorHandlerAutoConfiguration {

    private final ErrorAttributes errorAttributes;
    private final ServerProperties serverProperties;
    private final ResourceProperties resourceProperties;
    private final ApplicationContext applicationContext;
    private final ServerCodecConfigurer serverCodecConfigurer;
    private final MessageSource messageSource;
    private final HttpStatusExceptionConverter httpStatusExceptionConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebFluxErrorHandlerAutoConfiguration.class);

    @Autowired
    public WebFluxErrorHandlerAutoConfiguration(final ServerProperties serverProperties,
                                                final ResourceProperties resourceProperties,
                                                final ApplicationContext applicationContext,
                                                final ServerCodecConfigurer serverCodecConfigurer,
                                                final MessageSource messageSource,
                                                final HttpStatusExceptionConverter httpStatusConverter) {

        this.errorAttributes = new DefaultErrorAttributes();
        this.serverProperties = serverProperties;
        this.resourceProperties = resourceProperties;
        this.applicationContext = applicationContext;
        this.serverCodecConfigurer = serverCodecConfigurer;
        this.messageSource = messageSource;
        this.httpStatusExceptionConverter = httpStatusConverter;
    }

    @Bean
    public DefaultErrorWebExceptionHandler defaultErrorWebExceptionHandler() {
        return new WebExceptionHandler(
                this.errorAttributes,
                this.serverProperties,
                this.resourceProperties,
                this.applicationContext,
                this.serverCodecConfigurer,
                this.messageSource,
                this.httpStatusExceptionConverter
        );
    }

    public static class WebExceptionHandler extends DefaultErrorWebExceptionHandler {

        private final ErrorAttributes errorAttributes;
        private final HttpStatusExceptionConverter httpStatusConverter;
        private final MessageSource messageSource;

        private final List<Locale> LOCALE = Arrays.asList(Locale.ENGLISH, Locale.forLanguageTag("pt"));

        @Autowired
        public WebExceptionHandler(final ErrorAttributes errorAttributes,
                                   final ServerProperties serverProperties,
                                   final ResourceProperties resourceProperties,
                                   final ApplicationContext applicationContext,
                                   final ServerCodecConfigurer serverCodecConfigurer,
                                   final MessageSource messageSource,
                                   final HttpStatusExceptionConverter httpStatusConverter) {

            super(errorAttributes, resourceProperties, serverProperties.getError(), applicationContext);

            super.setMessageWriters(serverCodecConfigurer.getWriters());
            super.setMessageReaders(serverCodecConfigurer.getReaders());

            this.errorAttributes = errorAttributes;
            this.messageSource = messageSource;
            this.httpStatusConverter = httpStatusConverter;
        }

        @Override
        protected Mono<ServerResponse> renderErrorView(ServerRequest request) {
            return this.logAndConvert(request);
        }

        @Override
        protected Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {
            return this.logAndConvert(request);
        }

        private Mono<ServerResponse> logAndConvert(final ServerRequest request) {
            final Throwable throwable = this.errorAttributes.getError(request);
            LOGGER.error("Ocorreu um erro ao processar a requisição", throwable);
            final Locale locale = Optional.ofNullable(Locale.lookup(request.headers().acceptLanguage(), LOCALE)).orElse(Locale.forLanguageTag("pt"));


            final HttpStatus httpStatus = this.convertToHttpStatus(throwable);
            final ContractsException exception = mapException(throwable);

            final ErrorCode errorCode = exception.getErrorCode();

            final String message = messageSource.getMessage(errorCode.getCode(), errorCode.getParameters(), locale);

            final ErrorMessage errorMessage = new ErrorMessage(errorCode.getCode(), message);

            return ServerResponse.status(httpStatus.value())
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(errorMessage);
        }

        private ContractsException mapException(final Throwable throwable) {
            if (throwable instanceof ContractsException) {
                return (ContractsException) throwable;
            }
            return ExceptionUtils.technicalException(VehicleDataErrorCode.INFRA_ERROR_CODE);
        }

        private HttpStatus convertToHttpStatus(final Throwable throwable) {
            return Optional.of(throwable)
                    .filter(Predicates.instanceOf(DecodingException.class))
                    .map(t -> HttpStatus.BAD_REQUEST)
                    .orElse(HttpStatus.valueOf(this.httpStatusConverter.convert(throwable)));
        }
    }
}