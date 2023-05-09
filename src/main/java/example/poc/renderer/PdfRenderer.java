package example.poc.renderer;

import jakarta.inject.Singleton;
import org.ibfd.regionalxml.GenerateOption;
import org.ibfd.regionalxml.GenerateParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public final class PdfRenderer extends BaseRenderer {
    private static final Logger logger = LoggerFactory.getLogger(PdfRenderer.class);
    private static final String FORMAT_NAME = "pdf";
    private static final GenerateOption GENERATE_OPTION = new GenerateOption(GenerateParam.FULL_CHAPTER, "");

    public PdfRenderer() {
        logger.info("PdfRenderer is constructed");
    }

    @Override
    public String getFormatName() {
        return FORMAT_NAME;
    }

    @Override
    protected void configureTransformer() {
        getTransformer().setGenerateOption(GENERATE_OPTION);
        getTransformer().setOmitToc(true);
        getTransformer().setPrintVersion(false);
    }

}
