package org.ibfd.otcc.responsebuilder;

import org.ibfd.otcc.model.RenderRequest;
import io.micronaut.http.HttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

public sealed interface ResponseBuilder permits PdfResponseBuilder, PrintVersionResponseBuilder {
    String getFormatName();

    String getContentType();

    Map<CharSequence, CharSequence> getHeaders(RenderRequest request);

    Optional<byte[]> convertToFormat(String body, RenderRequest request) throws IOException;

    default HttpResponse<byte[]> buildResponse(String body, RenderRequest request) throws IOException {
        byte[] convertedOutput = convertToFormat(body, request).orElse(body.getBytes(StandardCharsets.UTF_8));
        return HttpResponse.ok(convertedOutput)
                .contentLength(convertedOutput.length)
                .contentType(getContentType())
                .header("Set-Cookie", "DEV_IBFD_SESSION="+request.getAuthKey()) // temporary header, not for production
                .headers(getHeaders(request));
    }
}
