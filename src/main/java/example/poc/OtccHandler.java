package example.poc;

import jakarta.inject.Singleton;
import example.poc.model.*;
import example.poc.renderer.BaseRenderer;
import example.poc.renderer.RendererFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Singleton
public class OtccHandler {

    private static final Logger logger = LoggerFactory.getLogger(OtccHandler.class);

    private final AppConfig appConfig;
    private final ExternalGateway externalGateway;
    private final RendererFactory rendererFactory;

    public OtccHandler(AppConfig appConfig, ExternalGateway externalGateway, RendererFactory rendererFactory) {
        this.appConfig = appConfig;
        this.externalGateway = externalGateway;
        this.rendererFactory = rendererFactory;
    }

    public String handle(RenderRequest request) throws IOException {
        SessionData sessionData = externalGateway.getSessionData(request.getAuthKey());
        LinkResolverRequest linkResolverRequest = new LinkResolverRequest(request, sessionData);
        LinkResolverData linkResolverData = externalGateway.getLinkResolverData(request, linkResolverRequest);
        RenderData renderData = new RenderData(appConfig, request, sessionData, linkResolverData);
        BaseRenderer renderer = rendererFactory.getRenderer(request.getFormat());
        renderer.render(renderData);
        // write output
        return "";
    }
}
