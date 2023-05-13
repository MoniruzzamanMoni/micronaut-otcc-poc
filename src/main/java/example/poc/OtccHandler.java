package example.poc;

import example.poc.cache.RendererCacheKeyGenerator;
import example.poc.model.LinkResolverRequest;
import example.poc.model.RenderData;
import example.poc.model.RenderRequest;
import example.poc.model.SearchQuery;
import example.poc.renderer.RendererFactory;
import io.micronaut.cache.annotation.Cacheable;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Cacheable(keyGenerator = RendererCacheKeyGenerator.class, cacheNames = "renderer")
    public String handle(RenderRequest request) throws Exception {
        var sessionData = externalGateway.getSessionData(request.getAuthKey());
        var searchQuery = new SearchQuery(request, sessionData);
        var linkResolverRequest = new LinkResolverRequest(searchQuery.getSearchQuery(), "uid", "50");
        var linkResolverData = externalGateway.getLinkResolverData(request, linkResolverRequest);
        var renderData = new RenderData(appConfig, request, sessionData, linkResolverData);
        var renderer = rendererFactory.getRenderer(request.getFormat());
        return renderer.render(renderData);
    }
}
