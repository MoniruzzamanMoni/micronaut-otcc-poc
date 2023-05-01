package moni.poc;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.reactivestreams.Publisher;

import javax.validation.Valid;

@Controller("/otcc")
public class OtccController {

    private OtccHandler otccHandler;
    private TestClientClient testClientClient;

    public OtccController(OtccHandler otccHandler, TestClientClient testClientClient) {
        this.otccHandler = otccHandler;
        this.testClientClient = testClientClient;
    }

    @Get(uri = "/fetch", produces = MediaType.APPLICATION_JSON)
    @SingleResult
    public Publisher<Message> fetch() {
        Publisher<Message> response = testClientClient.fetchSessionData();
        return response;
    }
/*

        response.subscribe(new Subscriber<Message>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("subscribed");
            }

            @Override
            public void onNext(Message message) {
                System.out.println("Next: " + message.toString());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });
* */
    @Get(uri="/{urlType}/{collection}/{format}/{fileName}.{ext}", produces="text/plain")
    public String index(@Valid @RequestBean RenderRequestBean request) {
        otccHandler.handle(request);
        return """
                Example Response
                =======================
                authKey: %s
                urlPart1: %s
                collection: %s
                format: %s
                fileName: %s
                ext: %s
                =======================
                """.formatted(request.getAuthKey(),
                                request.getUrlType(),
                                request.getCollection(),
                                request.getFormat(),
                                request.getFileName(),
                                request.getExt()
                );
    }
}