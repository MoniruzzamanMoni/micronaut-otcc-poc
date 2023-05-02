package moni.poc;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

@Singleton
public class OtccHandler {

    private static Logger logger = LoggerFactory.getLogger(OtccHandler.class);

    private AppConfig appConfig;
    private ExternalGateway externalGateway;

    public OtccHandler(AppConfig appConfig, ExternalGateway externalGateway) {
        this.appConfig = appConfig;
        this.externalGateway = externalGateway;
    }

    public void handle(RenderRequestBean request) throws MalformedURLException {
        // singleton api gateway
        SessionData sessionData = externalGateway.getSessionData(request.getAuthKey());
        logger.debug("## Session Data: " + sessionData);
        logger.debug("Username: " + sessionData.getUsername());
        logger.debug("Greet: " + sessionData.getGreet());
        logger.debug("Subs: " + sessionData.getSubscriptions());

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
