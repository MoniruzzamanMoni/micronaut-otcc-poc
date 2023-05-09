package example.poc.responsebuilder;

import example.poc.model.RenderRequest;
import io.micronaut.http.HttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

/**
 * author: Md. Moniruzzaman <moni.return@gmail.com>
 * since: 5/7/2023
 */
public interface ResponseBuilder {
    String getFormatName();

    String getContentType();

    Map<CharSequence, CharSequence> getHeaders(RenderRequest request);

    Optional<byte[]> convertToFormat(String body, RenderRequest request) throws IOException;

    default HttpResponse<byte[]> buildResponse(String body, RenderRequest request) throws IOException {
        byte[] convertedOutput = convertToFormat(body, request).orElse(body.getBytes(StandardCharsets.UTF_8));
        return HttpResponse.ok(convertedOutput)
                .contentLength(convertedOutput.length)
                .contentType(getContentType())
                .headers(getHeaders(request));
    }
}
