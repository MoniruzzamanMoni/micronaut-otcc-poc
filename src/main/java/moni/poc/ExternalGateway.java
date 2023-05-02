package moni.poc;

import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

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

    public Publisher<SessionData> getSessionData(String cookie) throws MalformedURLException {
        URL url = new URL(appConfig.getSessionManagerUrl());
        String body = "key=%s&action=read".formatted(cookie);
        HttpRequest<?> request = HttpRequest.POST(url.getPath(), body)
                .header(HttpHeaders.ACCEPT, "*/*")
                .header(HttpHeaders.HOST, url.getHost())
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(body.length()));

        Publisher<String> responseBody = sessionMangerClient.retrieve(request, String.class);

        CompletableFuture<SessionData> future = new CompletableFuture<>();
        responseBody.subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("on subs");
                subscription.request(1);
            }

            @Override
            public void onNext(String s) {
                System.out.println("on next: %s".formatted(s));
                future.complete(new SessionData(s));
            }

            @Override
            public void onError(Throwable throwable) {
                future.completeExceptionally(throwable);
            }

            @Override
            public void onComplete() {
                System.out.println("on complete");
            }
        });

        return Publishers.fromCompletableFuture(future);
    }
}
