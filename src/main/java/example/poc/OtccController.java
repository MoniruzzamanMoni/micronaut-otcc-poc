package example.poc;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.RequestBean;
import example.poc.model.RenderRequest;

import javax.validation.Valid;

@Controller("/otcc")
public class OtccController {

    private final OtccHandler otccHandler;

    public OtccController(OtccHandler otccHandler) {
        this.otccHandler = otccHandler;
    }

    @Get(uri="/{urlType}/{collection}/{format}/{fileName}.{ext}", produces = MediaType.ALL)
    public HttpResponse<String> render(@Valid @RequestBean RenderRequest request) throws Exception{
        return otccHandler.handle(request);
    }
}