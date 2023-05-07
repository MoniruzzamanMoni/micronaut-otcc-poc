package example.poc;

import example.poc.responsebuilder.ResponseBuilderFactory;
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
    private final ResponseBuilderFactory responseBuilderFactory;

    public OtccController(OtccHandler otccHandler, ResponseBuilderFactory responseBuilderFactory) {
        this.otccHandler = otccHandler;
        this.responseBuilderFactory = responseBuilderFactory;
    }

    @Get(uri="/{urlType}/{collection}/{format}/{fileName}.{ext}", produces = MediaType.ALL)
    public HttpResponse<byte[]> render(@Valid @RequestBean RenderRequest request) throws Exception{
        String output = otccHandler.handle(request);
        return responseBuilderFactory.getResponseBuilder(request.getFormat())
                .buildResponse(output, request);
    }
}