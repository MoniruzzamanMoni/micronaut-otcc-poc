package example.poc;

import example.poc.cache.RendererCacheKeyGenerator;
import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;
import example.poc.model.*;
import example.poc.renderer.BaseRenderer;
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
        SessionData sessionData = externalGateway.getSessionData(request.getAuthKey());
        LinkResolverRequest linkResolverRequest = new LinkResolverRequest(request, sessionData);
        LinkResolverData linkResolverData = externalGateway.getLinkResolverData(request, linkResolverRequest);
        RenderData renderData = new RenderData(appConfig, request, sessionData, linkResolverData);
        BaseRenderer renderer = rendererFactory.getRenderer(request.getFormat());
        return renderer.render(renderData);
    }
}
