package example.poc.exception;

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
public class RegionalXmlTransformerExceptionHandler implements ExceptionHandler<RegionalXmlTransformerException, HttpResponse> {

    private final ErrorResponseProcessor<?> errorResponseProcessor;

    public RegionalXmlTransformerExceptionHandler(ErrorResponseProcessor<?> errorResponseProcessor) {
        this.errorResponseProcessor = errorResponseProcessor;
    }

    @Override
    public HttpResponse handle(HttpRequest request, RegionalXmlTransformerException e) {
        return errorResponseProcessor.processResponse(ErrorContext.builder(request)
                .cause(e)
                .errorMessage("Regional Xml Transformer Error: %s".formatted(e.getMessage()))
                .build(), HttpResponse.serverError());
    }
}
