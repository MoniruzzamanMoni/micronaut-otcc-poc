package moni.poc.model;

import io.micronaut.core.util.StringUtils;
import io.micronaut.serde.annotation.Serdeable;
import moni.poc.RenderRequestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

/**
 * @author: Md. Moniruzzaman <moni.return@gmail.com>
 * @since: 5/3/2023
 */
@Serdeable
public class LinkResolverRequest {
    private static Logger logger = LoggerFactory.getLogger(LinkResolverRequest.class);
    private String search;
    private String properties;
    private String maxresults;

    public LinkResolverRequest(RenderRequestBean request, SessionData sessionData) {
        String recordFilter = sessionData.getSubscriptions().stream()
                .map(subscription -> "lcf:" + subscription)
                .collect(Collectors.toList())
                .stream().collect(Collectors.joining(","));
        String filename = "%s.%s".formatted(request.getFileName(), request.getExt());
        this.search = StringUtils.isEmpty(recordFilter) ?
                "N=0&Nr=xml_source_file:%s".formatted(filename) :
                "N=0&Nr=AND(xml_source_file:%s,OR(%s))".formatted(filename, recordFilter);
        this.properties = "uid";
        this.maxresults = "50";
        logger.debug("Linkresolver Request: %s".formatted(this));
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