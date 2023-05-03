package moni.poc;

import jakarta.inject.Singleton;
import moni.poc.model.LinkResolverData;
import moni.poc.model.LinkResolverRequest;
import moni.poc.model.RenderData;
import moni.poc.model.SessionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Singleton
public class OtccHandler {

    private static final Logger logger = LoggerFactory.getLogger(OtccHandler.class);

    private final AppConfig appConfig;
    private final ExternalGateway externalGateway;

    public OtccHandler(AppConfig appConfig, ExternalGateway externalGateway) {
        this.appConfig = appConfig;
        this.externalGateway = externalGateway;
    }

    public String handle(RenderRequestBean request) throws IOException {
        SessionData sessionData = externalGateway.getSessionData(request.getAuthKey());
        LinkResolverRequest linkResolverRequest = new LinkResolverRequest(request, sessionData);
        LinkResolverData linkResolverData = externalGateway.getLinkResolverData(request, linkResolverRequest);
        RenderData renderData = new RenderData(request, sessionData, linkResolverData, appConfig);

        // rendererData.initialize(authKey, urlPart1, collection, format, fileName, ext, uidList);

        // check cache
        // get renderer
        // render
        // write output
        return "";
    }
}
