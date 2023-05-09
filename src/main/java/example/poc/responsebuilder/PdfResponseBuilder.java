package example.poc.responsebuilder;

import example.poc.model.RenderRequest;
import jakarta.inject.Singleton;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * author: Md. Moniruzzaman <moni.return@gmail.com>
 * since: 5/7/2023
 */
@Singleton
public final class PdfResponseBuilder implements ResponseBuilder {
    private static final Logger logger = LoggerFactory.getLogger(PdfResponseBuilder.class);

    @Override
    public String getFormatName() {
        return "pdf";
    }

    @Override
    public String getContentType() {
        return "application/pdf; charset=UTF-8";
    }

    @Override
    public Map<CharSequence, CharSequence> getHeaders(RenderRequest request) {
        var map = new HashMap<CharSequence, CharSequence>();
        map.put("content-disposition", "inline; filename=\"%s.pdf\"".formatted(request.getFileName()));
        return map;
    }

    @Override
    public Optional<byte[]> convertToFormat(String htmlContent, RenderRequest request) throws IOException {
        try (var outputStream = new ByteArrayOutputStream()) {
            var documentHtml = getXhtml(htmlContent);
            var renderer = getRenderer(documentHtml);
            renderer.createPDF(outputStream);
            return Optional.of(outputStream.toByteArray());
        }
    }

    private ITextRenderer getRenderer(String documentHtml) {
        var renderer = new ITextRenderer();
        var sharedContext = renderer.getSharedContext();
        sharedContext.setPrint(true);
        sharedContext.setInteractive(false);
        renderer.setDocumentFromString(documentHtml);
        renderer.layout();
        return renderer;
    }

    private String getXhtml(String htmlContent) {
        var document = Jsoup.parse(htmlContent);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        document.charset(StandardCharsets.UTF_8);
        logger.debug("###  htmlContent first line: %s".formatted(htmlContent.lines().findFirst().orElse("")));
        var documentHtml = document.html()
                .replaceFirst("<!DOCTYPE html>",
                        "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" " +
                                "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        logger.debug("###  document html: %s".formatted(documentHtml.lines().findFirst().orElse("")));
        return documentHtml;
    }

}