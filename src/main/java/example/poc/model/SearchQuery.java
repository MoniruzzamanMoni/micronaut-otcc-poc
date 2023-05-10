package example.poc.model;

import io.micronaut.core.util.StringUtils;

import java.util.List;

public record SearchQuery(String fileName, String ext, List<String> subscriptions) {
    public String getSearchQuery() {
        var recordFilter = String.join(",", subscriptions.stream()
                .map(subscription -> "lcf:" + subscription).toList());
        return StringUtils.isEmpty(recordFilter) ?
                "N=0&Nr=xml_source_file:%s.%s".formatted(fileName, ext) :
                "N=0&Nr=AND(xml_source_file:%s,OR(%s))".formatted(fileName, recordFilter);
    }
}
