package moni.poc;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.reactivestreams.Publisher;

import javax.validation.Valid;
import java.net.MalformedURLException;

@Controller("/otcc")
public class OtccController {

    private OtccHandler otccHandler;
    private TestClientClient testClientClient;
    private ExternalGateway externalGateway;

    public OtccController(OtccHandler otccHandler, TestClientClient testClientClient, ExternalGateway externalGateway) {
        this.otccHandler = otccHandler;
        this.testClientClient = testClientClient;
        this.externalGateway = externalGateway;
    }

    @Get(uri = "/fetch", produces = MediaType.APPLICATION_JSON)
    @SingleResult
    public Publisher<Message> fetch() {
        return testClientClient.fetchSessionData();
    }

    @Get(uri = "/session-data", produces = MediaType.APPLICATION_JSON)
    public SessionData fetchSessionData(@CookieValue("DEV_IBFD_SESSION") String cookie) throws MalformedURLException {
        return externalGateway.getSessionData(cookie);
    }

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