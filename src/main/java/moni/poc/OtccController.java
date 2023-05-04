package moni.poc;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.RequestBean;
import moni.poc.model.RenderRequest;

import javax.validation.Valid;

@Controller("/otcc")
public class OtccController {

    private OtccHandler otccHandler;

    public OtccController(OtccHandler otccHandler) {
        this.otccHandler = otccHandler;
    }

    @Get(uri="/{urlType}/{collection}/{format}/{fileName}.{ext}", produces = MediaType.APPLICATION_XML)
    public String index(@Valid @RequestBean RenderRequest request) throws Exception{
        return otccHandler.handle(request);
    }
}