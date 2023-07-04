package org.ibfd.otcc.renderer;

import org.ibfd.otcc.cache.RendererCacheKeyGenerator;
import org.ibfd.otcc.model.RenderData;
import io.micronaut.cache.annotation.Cacheable;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class RendererOutputHandler {
    private static final Logger logger = LoggerFactory.getLogger(RendererOutputHandler.class);
    private final RendererFactory rendererFactory;

    public RendererOutputHandler(RendererFactory rendererFactory) {
        this.rendererFactory = rendererFactory;
    }

    @Cacheable(keyGenerator = RendererCacheKeyGenerator.class, cacheNames = "renderer")
    public String getRenderedOutput(RenderData renderData, String format) throws Exception {
        logger.info("### RENDERING...");
        var renderer = rendererFactory.getRenderer(format);
        return renderer.render(renderData);
    }
}
