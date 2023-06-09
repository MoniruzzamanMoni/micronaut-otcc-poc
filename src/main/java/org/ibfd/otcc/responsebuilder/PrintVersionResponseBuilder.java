package org.ibfd.otcc.responsebuilder;

import org.ibfd.otcc.model.RenderRequest;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public final class PrintVersionResponseBuilder implements ResponseBuilder {

    @Override
    public String getFormatName() {
        return "printversion";
    }

    @Override
    public String getContentType() {
        return "text/html; charset=UTF-8";
    }

    @Override
    public Map<CharSequence, CharSequence> getHeaders(RenderRequest request) {
        Map<CharSequence, CharSequence> map = new HashMap<>();
        map.put("content-disposition", "inline; filename=\"%s.%s\"".formatted(request.getFileName(), request.getExt()));
        return map;
    }

    @Override
    public Optional<byte[]> convertToFormat(String body, RenderRequest request) {
        return Optional.empty();
    }
}
