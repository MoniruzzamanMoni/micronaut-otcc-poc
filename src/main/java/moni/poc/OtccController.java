package moni.poc;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.CookieValue;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.RequestBean;
import moni.poc.model.SessionData;
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

    @Get(uri="/{urlType}/{collection}/{format}/{fileName}.{ext}", produces = MediaType.APPLICATION_XML)
    public String index(@Valid @RequestBean RenderRequestBean request) throws Exception{
        return otccHandler.handle(request);
    }
}