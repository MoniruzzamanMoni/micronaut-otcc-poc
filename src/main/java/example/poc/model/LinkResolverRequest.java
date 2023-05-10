package example.poc.model;

import io.micronaut.serde.annotation.Serdeable;

/**
 * author: Md. Moniruzzaman <moni.return@gmail.com>
 * since: 5/3/2023
 */
@Serdeable
public record LinkResolverRequest(String search, String properties, String maxresults) {
}
