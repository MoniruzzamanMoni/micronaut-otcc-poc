package example.poc.renderer;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import jakarta.inject.Singleton;
import org.ibfd.regionalxml.GenerateOption;
import org.ibfd.regionalxml.GenerateParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Singleton
public final class PrintVersionRenderer extends BaseRenderer {
    private static final Logger logger = LoggerFactory.getLogger(PrintVersionRenderer.class);
    private static final String FORMAT_NAME = "printversion";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final GenerateOption GENERATE_OPTION = new GenerateOption(GenerateParam.FULL_CHAPTER, "");

    public PrintVersionRenderer() {
        logger.info("PrintVersionRenderer construct");
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
        getTransformer().setDocidErrNoExit(true);
        getTransformer().setXmlFileName(getRenderData().getSrcXmlFileName());
        getTransformer().setLimaserver(getRenderData().getLimaServerBaseUrl());
        String csvUids = (getRenderData().getUids() == null)
                            ? "" : String.join(",", getRenderData().getUids());
        getTransformer().setCsvUids(csvUids);
    }

    @Override
    protected HttpResponse<String> buildResponse() throws IOException {
        MutableHttpResponse<String> response = HttpResponse.ok(getTransformedResult().toString())
                .contentLength(getTransformedResult().toByteArray().length)
                .contentType(CONTENT_TYPE);
        getTransformedResult().close();
        return response;
    }
}
