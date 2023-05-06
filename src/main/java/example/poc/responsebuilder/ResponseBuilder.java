package example.poc.responsebuilder;

import example.poc.model.RenderRequest;
import io.micronaut.http.HttpResponse;

import java.util.Map;

/**
 * @author: Md. Moniruzzaman <moni.return@gmail.com>
 * @since: 5/7/2023
 */
public interface ResponseBuilder {
    String getFormatName();

    String getContentType();

    Map<CharSequence, CharSequence> getHeaders(RenderRequest request);

    default HttpResponse<String> buildResponse(String body, RenderRequest request) {
        return HttpResponse.ok(body)
                .contentLength(body.length())
                .contentType(getContentType())
                .headers(getHeaders(request));
    }
}
