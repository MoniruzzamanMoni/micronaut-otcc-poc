package example.poc.renderer;

import example.poc.model.RenderData;
import org.ibfd.regionalxml.RegionalXmlTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

public abstract sealed class BaseRenderer permits PdfRenderer, PrintVersionRenderer {
    private static final Logger logger = LoggerFactory.getLogger(BaseRenderer.class);

    private RegionalXmlTransformer transformer;
    private ByteArrayOutputStream transformedResult;

    public RegionalXmlTransformer getTransformer() {
        return transformer;
    }

    public ByteArrayOutputStream getTransformedResult() {
        return transformedResult;
    }

    public final void render(RenderData renderData) {
        logger.info("BaseRenderer render");
        initializeTransformer(renderData);
        configureTransformer(renderData);
        transform();
        writeHtmlBytes();
    }

    protected abstract void configureTransformer(RenderData renderData);

    protected abstract void writeHtmlBytes();

    private void transform() {

    }

    private void initializeTransformer(RenderData renderData) {

    }
}
