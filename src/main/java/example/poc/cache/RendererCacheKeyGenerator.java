package example.poc.cache;

import example.poc.model.RenderRequest;
import io.micronaut.cache.interceptor.CacheKeyGenerator;
import io.micronaut.core.annotation.AnnotationMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: Md. Moniruzzaman <moni.return@gmail.com>
 * since: 5/7/2023
 */
public class RendererCacheKeyGenerator implements CacheKeyGenerator {

    private static final Logger logger = LoggerFactory.getLogger(RendererCacheKeyGenerator.class);

    @Override
    public Object generateKey(AnnotationMetadata annotationMetadata, Object... params) {
        if (params[0] instanceof RenderRequest req) {
            var key = buildKey(req);
            logger.debug("Generated cache key: %s".formatted(key));
            return key;
        } else {
            return null;
        }
    }

    private String buildKey(RenderRequest request) {
        return "%s/%s/%s.%s".formatted(
                request.getUrlType(),
                request.getCollection(),
                request.getFileName(),
                request.getExt());
    }
}
