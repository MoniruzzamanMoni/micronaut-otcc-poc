package org.ibfd.otcc.renderer;

import jakarta.inject.Singleton;
import org.ibfd.regionalxml.GenerateOption;
import org.ibfd.regionalxml.GenerateParam;
import org.ibfd.regionalxml.RegionalXmlTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public final class PrintVersionRenderer implements Renderer {
    private static final Logger logger = LoggerFactory.getLogger(PrintVersionRenderer.class);
    private static final String FORMAT_NAME = "printversion";
    private static final GenerateOption GENERATE_OPTION = new GenerateOption(GenerateParam.FULL_CHAPTER, "");

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public String getFormatName() {
        return FORMAT_NAME;
    }

    @Override
    public void configureTransformer(RegionalXmlTransformer transformer) {
        transformer.setGenerateOption(GENERATE_OPTION);
        transformer.setOmitToc(false);
        transformer.setPrintVersion(true);
    }

}
