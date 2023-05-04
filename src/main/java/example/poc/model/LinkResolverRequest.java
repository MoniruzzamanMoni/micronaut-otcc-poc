package example.poc.model;

import io.micronaut.core.util.StringUtils;
import io.micronaut.serde.annotation.Serdeable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

/**
 * @author: Md. Moniruzzaman <moni.return@gmail.com>
 * @since: 5/3/2023
 */
@Serdeable
public class LinkResolverRequest {
    private static final Logger logger = LoggerFactory.getLogger(LinkResolverRequest.class);
    private final String search;
    private final String properties;
    private final String maxresults;

    public LinkResolverRequest(RenderRequest request, SessionData sessionData) {
        String recordFilter = String.join(",", sessionData.getSubscriptions().stream()
                .map(subscription -> "lcf:" + subscription).toList());
        String filename = "%s.%s".formatted(request.getFileName(), request.getExt());
        this.search = StringUtils.isEmpty(recordFilter) ?
                "N=0&Nr=xml_source_file:%s".formatted(filename) :
                "N=0&Nr=AND(xml_source_file:%s,OR(%s))".formatted(filename, recordFilter);
        this.properties = "uid";
        this.maxresults = "50";

        logger.debug("LinkResolverRequest is constructed: %s".formatted(this));
    }

    public String getSearch() {
        return search;
    }

    public String getProperties() {
        return properties;
    }

    public String getMaxresults() {
        return maxresults;
    }

    @Override
    public String toString() {
        return "LinkResolverRequest{" +
                "search='" + search + '\'' +
                ", properties='" + properties + '\'' +
                ", maxresults='" + maxresults + '\'' +
                '}';
    }
}