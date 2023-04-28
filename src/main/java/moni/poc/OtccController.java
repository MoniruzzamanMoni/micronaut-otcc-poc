package moni.poc;

import io.micronaut.http.annotation.*;

@Controller("/otcc")
public class OtccController {

    private final AppConfig appConfig;

    public OtccController(AppConfig appConfig) {
        this.appConfig = appConfig;
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
                LimaserverBaseUrl: %s
                LinkresolverBaseUrl: %s
                LinkresolverUsePost: %s
                PublicationBasePath: %s
                RegionalPdfXslUrl: %s
                SessionCookieName: %s
                """.formatted(authKey,
                                urlPart1,
                                collection,
                                format,
                                fileName,
                                ext,
                                appConfig.getLimaserverBaseUrl(),
                                appConfig.getLinkresolverBaseUrl(),
                                appConfig.getLinkresolverUsePost(),
                                appConfig.getPublicationBasePath(),
                                appConfig.getRegionalPdfXslUrl(),
                                appConfig.getSessionCookieName()
                );
    }
}