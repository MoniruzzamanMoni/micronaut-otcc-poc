package example.poc.renderer;

import jakarta.inject.Singleton;
import org.ibfd.regionalxml.GenerateOption;
import org.ibfd.regionalxml.GenerateParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public final class PrintVersionRenderer extends BaseRenderer {
    private static final Logger logger = LoggerFactory.getLogger(PrintVersionRenderer.class);

    public PrintVersionRenderer() {
        logger.info("PrintVersionRenderer construct");
    }

    @Override
    protected void configureTransformer() {
        getTransformer().setGenerateOption(new GenerateOption(GenerateParam.FULL_CHAPTER, ""));
        getTransformer().setOmitToc(false);
        getTransformer().setPrintVersion(true);
        getTransformer().setDocidErrNoExit(true);
        getTransformer().setXmlFileName(getRenderData().getSrcXmlFileName());
        getTransformer().setLimaserver(getRenderData().getLimaServerBaseUrl());
        String csvUids = (getRenderData().getUids() == null)
                            ? "" : String.join(",", getRenderData().getUids());
        getTransformer().setCsvUids(csvUids);
    }

    @Override
    protected void writeHtmlBytes() {
//        this.response.setContentType("text/html; charset=UTF-8");
//        this.response.setContentLength(bytes.length);
    }
}
