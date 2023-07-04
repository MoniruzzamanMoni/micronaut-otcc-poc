package org.ibfd.otcc.model;

import io.micronaut.core.util.StringUtils;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record SearchQuery(RenderRequest request, SessionData sessionData) {

    public String getSearchQuery() {
        var recordFilter =
                Stream.of(sessionData.getSubSubscriptions()
                    .stream()
                    .map(subscription -> "lcf:" + subscription)
                    .toList(),
                    sessionData.getPsubSubscriptions(),
                    sessionData.getVsubSubscriptions()
                )
                .flatMap(Collection::stream)
                .collect(Collectors.joining(","));

        return StringUtils.isEmpty(recordFilter) ?
                "N=0&Nr=xml_source_file:%s.%s".formatted(request.getFileName(), request.getExt()) :
                "N=0&Nr=AND(xml_source_file:%s.%s,OR(%s))".formatted(request.getFileName(), request.getExt(), recordFilter);
    }
}
