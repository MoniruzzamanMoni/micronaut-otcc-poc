package example.poc.model;

import io.micronaut.core.util.StringUtils;
import io.micronaut.serde.annotation.Serdeable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Serdeable
public class LinkResolverData {
    private static final Logger logger = LoggerFactory.getLogger(LinkResolverData.class);
    private static final String PATTERN = "<property name=\"uid\">([a-zA-Z0-9\\._\\-]+)</property>";

    private final List<String> uids;

    public LinkResolverData(String content) {
        this.uids = parseUids(content);

        logger.debug("LinkResolverData is constructed: %s".formatted(this));
    }

    public List<String> getUids() {
        return uids;
    }

    private List<String> parseUids(String content) {
        return Pattern.compile(PATTERN).matcher(content).results()
                .map(matchResult -> matchResult.group(1))
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "LinkResolverData{" +
                "uids=" + uids +
                '}';
    }
}
