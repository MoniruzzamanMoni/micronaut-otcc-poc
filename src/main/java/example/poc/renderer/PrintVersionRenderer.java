package example.poc.renderer;

import jakarta.inject.Singleton;
import org.ibfd.regionalxml.GenerateOption;
import org.ibfd.regionalxml.GenerateParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public final class PrintVersionRenderer extends BaseRenderer {
    private static final Logger logger = LoggerFactory.getLogger(PrintVersionRenderer.class);
    private static final String FORMAT_NAME = "printversion";
    private static final GenerateOption GENERATE_OPTION = new GenerateOption(GenerateParam.FULL_CHAPTER, "");

    public PrintVersionRenderer() {
        logger.info("PrintVersionRenderer is construct");
    }

    @Override
    public String getFormatName() {
        return FORMAT_NAME;
    }

    @Override
    protected void configureTransformer() {
        getTransformer().setGenerateOption(GENERATE_OPTION);
        getTransformer().setOmitToc(false);
        getTransformer().setPrintVersion(true);
    }

}
