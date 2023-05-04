package example.poc.renderer;

import example.poc.model.RenderData;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public final class PrintVersionRenderer extends BaseRenderer {
    private static final Logger logger = LoggerFactory.getLogger(PrintVersionRenderer.class);

    public PrintVersionRenderer() {
        logger.info("PrintVersionRenderer construct");
    }

    @Override
    protected void configureTransformer(RenderData renderData) {

    }

    @Override
    protected void writeHtmlBytes() {

    }
}
