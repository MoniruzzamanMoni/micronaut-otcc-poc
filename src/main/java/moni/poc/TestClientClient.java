package moni.poc;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;
import org.reactivestreams.Publisher;

@Client("testClient")
public interface TestClientClient {

    @Get("/api/breeds/image/random")
    Publisher<Message> fetchSessionData();
}