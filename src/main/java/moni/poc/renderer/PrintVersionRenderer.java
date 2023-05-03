package moni.poc.renderer;

import jakarta.inject.Singleton;
import moni.poc.model.RenderData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class PrintVersionRenderer implements BaseRenderer {
    private static final Logger logger = LoggerFactory.getLogger(PrintVersionRenderer.class);

    public PrintVersionRenderer() {
        logger.info("PrintVersionRenderer construct");
    }

    @Override
    public void render(RenderData renderData) {
        logger.info("PrintVersionRenderer render");
    }
}
