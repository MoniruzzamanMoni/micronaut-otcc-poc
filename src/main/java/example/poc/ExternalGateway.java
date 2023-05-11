package example.poc;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Singleton;
import example.poc.model.LinkResolverData;
import example.poc.model.LinkResolverRequest;
import example.poc.model.RenderRequest;
import example.poc.model.SessionManagerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Singleton
public class ExternalGateway {
    private final AppConfig appConfig;
    private final HttpClient sessionMangerClient;
    private final HttpClient linkResolverClient;

    public ExternalGateway(AppConfig appConfig,
                           @Client("sessionManagerClient") HttpClient sessionMangerClient,
                           @Client("linkResolverClient") HttpClient linkResolverClient) {
        this.appConfig = appConfig;
        this.sessionMangerClient = sessionMangerClient;
        this.linkResolverClient = linkResolverClient;
    }

    public SessionManagerResponse getSessionManagerResponse(String authKey) throws MalformedURLException {
        var url = new URL(appConfig.getSessionManagerUrl());
        var body = "key=%s&action=read".formatted(authKey);
        HttpRequest<?> request = HttpRequest.POST(url.getPath(), body)
                .header(HttpHeaders.ACCEPT, "*/*")
                .header(HttpHeaders.HOST, url.getHost())
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(body.length()));

        Mono<String> responseBody = Mono.from(sessionMangerClient.retrieve(request, String.class));
        return new SessionManagerResponse(responseBody.blockOptional().orElse(""));
    }

    public LinkResolverData getLinkResolverData(RenderRequest request, LinkResolverRequest linkResolverRequest)
            throws IOException {
        var url = new URL(appConfig.getLinkresolverBaseUrl());
        var cookie = "%s=%s".formatted(appConfig.getSessionCookieName(), request.getAuthKey());
        HttpRequest<?> httpRequest = HttpRequest.POST(url.getPath(), linkResolverRequest)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.COOKIE, cookie);

        Mono<String> responseBody = Mono.from(linkResolverClient.retrieve(httpRequest, String.class));
        String content = responseBody.blockOptional().orElse("");

        return new LinkResolverData(content);
    }

}
