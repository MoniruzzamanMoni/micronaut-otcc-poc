package moni.poc;

import io.micronaut.http.annotation.*;

import javax.validation.Valid;

@Controller("/otcc")
public class OtccController {

    private final AppConfig appConfig;

    public OtccController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Get(uri="/{urlType}/{collection}/{format}/{fileName}.{ext}", produces="text/plain")
    public String index(@Valid @RequestBean RenderRequestBean request) {
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
                """.formatted(request.getAuthKey(),
                                request.getUrlType(),
                                request.getCollection(),
                                request.getFormat(),
                                request.getFileName(),
                                request.getExt(),
                                appConfig.getLimaserverBaseUrl(),
                                appConfig.getLinkresolverBaseUrl(),
                                appConfig.getLinkresolverUsePost(),
                                appConfig.getPublicationBasePath(),
                                appConfig.getRegionalPdfXslUrl(),
                                appConfig.getSessionCookieName()
                );
    }
}