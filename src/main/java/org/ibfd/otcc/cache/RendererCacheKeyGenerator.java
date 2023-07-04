package org.ibfd.otcc.cache;

import org.ibfd.otcc.model.RenderData;
import io.micronaut.cache.interceptor.CacheKeyGenerator;
import io.micronaut.core.annotation.AnnotationMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RendererCacheKeyGenerator implements CacheKeyGenerator {

    private static final Logger logger = LoggerFactory.getLogger(RendererCacheKeyGenerator.class);

    @Override
    public Object generateKey(AnnotationMetadata annotationMetadata, Object... params) {
        if (params[0] instanceof RenderData renderData) {
            var key = buildKey(renderData);
            logger.debug("Generated cache key: %s".formatted(key));
            return key;
        } else {
            return null;
        }
    }

    private String buildKey(RenderData renderData) {
        return renderData.getSrcXml();
    }
}
