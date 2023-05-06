package example.poc.responsebuilder;

import example.poc.model.RenderRequest;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Md. Moniruzzaman <moni.return@gmail.com>
 * @since: 5/7/2023
 */
@Singleton
public class PdfResponseBuilder implements ResponseBuilder {

    @Override
    public String getFormatName() {
        return "pdf";
    }

    @Override
    public String getContentType() {
        return "application/pdf; charset=UTF-8";
    }

    @Override
    public Map<CharSequence, CharSequence> getHeaders(RenderRequest request) {
        Map<CharSequence, CharSequence> map = new HashMap<>();
        map.put("content-disposition", "inline; filename=\"%s.%s\"".formatted(request.getFileName(), request.getExt()));
        return map;
    }
}
