package moni.poc;

import io.micronaut.http.annotation.*;

import javax.validation.Valid;

@Controller("/otcc")
public class OtccController {

    private OtccHandler otccHandler;

    public OtccController(OtccHandler otccHandler) {
        this.otccHandler = otccHandler;
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