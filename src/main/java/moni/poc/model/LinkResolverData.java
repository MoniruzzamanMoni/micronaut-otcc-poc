package moni.poc.model;

import io.micronaut.core.util.StringUtils;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Serdeable
public class LinkResolverData {
    private static final String PATTERN = "<property name=\"uid\">([a-zA-Z0-9\\._\\-]+)</property>";

    private final List<String> uids;

    public LinkResolverData(String content) {
        this.uids = Pattern.compile(PATTERN).matcher(content).results()
                .map(matchResult -> matchResult.group(1))
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
    }

    public List<String> getUids() {
        return uids;
    }
}
