package moni.poc.renderer;

import jakarta.inject.Singleton;
import moni.poc.model.RenderData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class PdfRenderer implements BaseRenderer {
    private static final Logger logger = LoggerFactory.getLogger(PdfRenderer.class);

    public PdfRenderer() {
        logger.info("PdfRenderer construct");
    }

    @Override
    public void render(RenderData renderData) {
        logger.info("PdfRenderer render");
    }
}
