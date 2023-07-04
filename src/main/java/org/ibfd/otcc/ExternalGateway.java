package org.ibfd.otcc;

import org.ibfd.otcc.exception.ConfigurationException;
import org.ibfd.otcc.exception.ExternalGatewayException;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Singleton;
import org.ibfd.otcc.model.LinkResolverData;
import org.ibfd.otcc.model.LinkResolverRequest;
import org.ibfd.otcc.model.RenderRequest;
import org.ibfd.otcc.model.SessionData;

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
        String responseBody = getSessionaManagerResponse(request);
        return new SessionData(responseBody);
    }

    public LinkResolverData getLinkResolverData(RenderRequest renderRequest, LinkResolverRequest linkResolverRequest) throws Exception {
        URL url = getLinkResolverBaseUrl();
        var cookie = "%s=%s".formatted(appConfig.getSessionCookieName(), renderRequest.getAuthKey());
        HttpRequest<?> httpRequest = HttpRequest.POST(url.getPath(), linkResolverRequest)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.COOKIE, cookie);
        String content = getLinkResolverResponse(httpRequest);
        return new LinkResolverData(content);
    }

    private String getLinkResolverResponse(HttpRequest<?> request) throws Exception {
        try {
            return linkResolverClient.toBlocking().retrieve(request, String.class);
        } catch (Exception e) {
            throw new ExternalGatewayException(e.getMessage());
        }
    }

    private String getSessionaManagerResponse(HttpRequest<?> request) throws Exception {
        try {
            return sessionMangerClient.toBlocking().retrieve(request, String.class);
        } catch (Exception e) {
            throw new ExternalGatewayException(e.getMessage());
        }
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
