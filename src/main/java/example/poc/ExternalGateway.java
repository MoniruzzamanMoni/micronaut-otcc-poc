package example.poc;

import example.poc.exception.ConfigurationException;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Singleton;
import example.poc.model.LinkResolverData;
import example.poc.model.LinkResolverRequest;
import example.poc.model.RenderRequest;
import example.poc.model.SessionData;
import reactor.core.publisher.Mono;

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

    public SessionData getSessionData(String authKey) throws Exception {
        URL url = getSessionManagerUrl();
        var body = "key=%s&action=read".formatted(authKey);
        HttpRequest<?> request = HttpRequest.POST(url.getPath(), body)
                .header(HttpHeaders.ACCEPT, "*/*")
                .header(HttpHeaders.HOST, url.getHost())
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(body.length()));

        Mono<String> responseBody = Mono.from(sessionMangerClient.retrieve(request, String.class));
        return new SessionData(responseBody.blockOptional().orElse(""));
    }


    public LinkResolverData getLinkResolverData(RenderRequest request, LinkResolverRequest linkResolverRequest) throws Exception {
        URL url = getLinkResolverBaseUrl();
        var cookie = "%s=%s".formatted(appConfig.getSessionCookieName(), request.getAuthKey());
        HttpRequest<?> httpRequest = HttpRequest.POST(url.getPath(), linkResolverRequest)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.COOKIE, cookie);

        Mono<String> responseBody = Mono.from(linkResolverClient.retrieve(httpRequest, String.class));
        String content = responseBody.blockOptional().orElse("");

        return new LinkResolverData(content);
    }

    private URL getLinkResolverBaseUrl() throws ConfigurationException {
        try {
            return new URL(appConfig.getLinkresolverBaseUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new ConfigurationException("Link Resolver Base URL is not configured in good form.", e);
        }
    }

    private URL getSessionManagerUrl() throws ConfigurationException {
        try {
            return new URL(appConfig.getSessionManagerUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new ConfigurationException("Session Manager URL is not configured in good form.", e);
        }
    }

}
