package moni.poc;

import jakarta.inject.Singleton;
import moni.poc.model.LinkResolverRequest;
import moni.poc.model.SessionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Singleton
public class OtccHandler {

    private static Logger logger = LoggerFactory.getLogger(OtccHandler.class);

    private AppConfig appConfig;
    private ExternalGateway externalGateway;

    public OtccHandler(AppConfig appConfig, ExternalGateway externalGateway) {
        this.appConfig = appConfig;
        this.externalGateway = externalGateway;
    }

    public String handle(RenderRequestBean request) throws IOException {
        // singleton api gateway
        SessionData sessionData = externalGateway.getSessionData(request.getAuthKey());
        LinkResolverRequest linkResolverRequest = new LinkResolverRequest(request, sessionData);
        return externalGateway.getLinkResolverData(request, linkResolverRequest);
//        logger.debug("### ### res: %s".formatted(res));

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
