package moni.poc;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URL;

@Singleton
public class ExternalGateway {
    private AppConfig appConfig;
    private HttpClient sessionMangerClient;
    private HttpClient limaServerClient;
    private HttpClient linkResolverClient;

    public ExternalGateway(AppConfig appConfig,
                           @Client("sessionManagerClient") HttpClient sessionMangerClient,
                           @Client("limaServerClient") HttpClient limaServerClient,
                           @Client("limaServerClient") HttpClient linkResolverClient) {
        this.appConfig = appConfig;
        this.sessionMangerClient = sessionMangerClient;
        this.limaServerClient = limaServerClient;
        this.linkResolverClient = linkResolverClient;
    }

    public SessionData getSessionData(String cookie) throws MalformedURLException {
        URL url = new URL(appConfig.getSessionManagerUrl());
        String body = "key=%s&action=read".formatted(cookie);
        HttpRequest<?> request = HttpRequest.POST(url.getPath(), body)
                .header(HttpHeaders.ACCEPT, "*/*")
                .header(HttpHeaders.HOST, url.getHost())
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(body.length()));

        Mono<String> responseBody = Mono.from(sessionMangerClient.retrieve(request, String.class));
        return new SessionData(responseBody.blockOptional().orElse(""));
    }
}
