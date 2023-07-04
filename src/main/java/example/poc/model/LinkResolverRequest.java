package example.poc.model;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record LinkResolverRequest(String search, String properties, String maxresults) {}
