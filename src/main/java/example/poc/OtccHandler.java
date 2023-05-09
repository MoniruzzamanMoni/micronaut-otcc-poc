package example.poc;

import example.poc.cache.RendererCacheKeyGenerator;
import example.poc.renderer.Renderer;
import io.micronaut.cache.annotation.Cacheable;
import jakarta.inject.Singleton;
import example.poc.model.*;
import example.poc.renderer.RendererFactory;
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
        var linkResolverRequest = new LinkResolverRequest(request, sessionData);
        var linkResolverData = externalGateway.getLinkResolverData(request, linkResolverRequest);
        var renderData = new RenderData(appConfig, request, sessionData, linkResolverData);
        var renderer = rendererFactory.getRenderer(request.getFormat());
        return renderer.render(renderData);
    }
}
