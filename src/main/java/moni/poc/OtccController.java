package moni.poc;

import io.micronaut.http.annotation.*;

@Controller("/otcc")
public class OtccController {

    private OtccHandler otccHandler;
    private RendererData rendererData;

    public OtccController(OtccHandler otccHandler, RendererData rendererData) {
        this.otccHandler = otccHandler;
        this.rendererData = rendererData;
    }

    @Get(uri="/{urlPart1}/{collection}/{format}/{fileName}.{ext}", produces="text/plain")
    public String index(
            @CookieValue("DEV_IBFD_SESSION") String authKey,
            @PathVariable("urlPart1") String urlPart1,
            @PathVariable("collection") String collection,
            @PathVariable("format") String format,
            @PathVariable("fileName") String fileName,
            @PathVariable("ext") String ext
    ) {
        rendererData.initialize(authKey, urlPart1, collection, format, fileName, ext);
        otccHandler.generate(rendererData);
        return """
                Example Response
                =======================
                authKey: %s
                urlPart1: %s
                collection: %s
                format: %s
                fileName: %s
                ext: %s
                """.formatted(authKey,
                                urlPart1,
                                collection,
                                format,
                                fileName,
                                ext
                );
    }
}