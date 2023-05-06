package example.poc.renderer;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public final class PdfRenderer extends BaseRenderer {
    private static final Logger logger = LoggerFactory.getLogger(PdfRenderer.class);
    private static final String FORMAT_NAME = "pdf";

    public PdfRenderer() {
        logger.info("PdfRenderer construct");
    }

    @Override
    public String getFormatName() {
        return FORMAT_NAME;
    }

    @Override
    protected void configureTransformer() {

    }

    @Override
    protected HttpResponse<String> buildResponse() {
        return HttpResponse.ok();
    }
}
