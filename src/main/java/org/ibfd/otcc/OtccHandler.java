package org.ibfd.otcc;

import org.ibfd.otcc.model.LinkResolverRequest;
import org.ibfd.otcc.model.RenderData;
import org.ibfd.otcc.model.RenderRequest;
import org.ibfd.otcc.model.SearchQuery;
import org.ibfd.otcc.renderer.RendererOutputHandler;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class OtccHandler {

    private static final Logger logger = LoggerFactory.getLogger(OtccHandler.class);

    private final AppConfig appConfig;
    private final ExternalGateway externalGateway;
    private final RendererOutputHandler rendererOutputHandler;

    public OtccHandler(AppConfig appConfig, ExternalGateway externalGateway, RendererOutputHandler rendererOutputHandler) {
        this.appConfig = appConfig;
        this.externalGateway = externalGateway;
        this.rendererOutputHandler = rendererOutputHandler;
    }

    public String handle(RenderRequest request) throws Exception {
        var sessionData = externalGateway.getSessionData(request.getAuthKey());
        var searchQuery = new SearchQuery(request, sessionData);
        var linkResolverRequest = new LinkResolverRequest(searchQuery.getSearchQuery(), "uid", "50");
        var linkResolverData = externalGateway.getLinkResolverData(request, linkResolverRequest);
        var renderData = new RenderData(appConfig, request, sessionData, linkResolverData);
        return rendererOutputHandler.getRenderedOutput(renderData, request.getFormat());
    }
}
