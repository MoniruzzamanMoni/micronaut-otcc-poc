package org.ibfd.otcc.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {ConfigurationException.class, ExceptionHandler.class})
public class ExternalGatewayExceptionHandler implements ExceptionHandler<ExternalGatewayException, HttpResponse> {

    private final ErrorResponseProcessor<?> errorResponseProcessor;

    public ExternalGatewayExceptionHandler(ErrorResponseProcessor<?> errorResponseProcessor) {
        this.errorResponseProcessor = errorResponseProcessor;
    }

    @Override
    public HttpResponse handle(HttpRequest request, ExternalGatewayException e) {
        return errorResponseProcessor.processResponse(ErrorContext.builder(request)
                .cause(e)
                .errorMessage("External Gateway Error: %s".formatted(e.getMessage()))
                .build(), HttpResponse.serverError());
    }
}
