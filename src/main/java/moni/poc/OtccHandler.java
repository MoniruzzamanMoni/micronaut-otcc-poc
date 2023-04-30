package moni.poc;

import jakarta.inject.Singleton;

@Singleton
public class OtccHandler {

    private AppConfig appConfig;

    public OtccHandler(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public void handle(RenderRequestBean request) {
        System.out.println("handle");
        System.out.println("""
                Example Response
                ======================= request
                authKey: %s
                urlPart1: %s
                collection: %s
                format: %s
                fileName: %s
                ext: %s
                ======================= config
                LimaserverBaseUrl: %s
                LinkresolverBaseUrl: %s
                LinkresolverUsePost: %s
                PublicationBasePath: %s
                RegionalPdfXslUrl: %s
                SessionCookieName: %s
                =======================
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
        ));
        // singleton api gateway
        // bean authorized uids retriever
        // List<String> uidList = uidRetriever.getUids();
        // List<String> uidList = new ArrayList<>();
        // bean session manager
        // sessionData
        // rendererData.initialize(authKey, urlPart1, collection, format, fileName, ext, uidList);

        // check cache
        // get renderer
        // render
        // write output
    }
}
