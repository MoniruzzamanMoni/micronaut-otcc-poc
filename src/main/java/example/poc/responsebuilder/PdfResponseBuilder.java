package example.poc.responsebuilder;

import example.poc.AppConfig;
import example.poc.model.RenderRequest;
import jakarta.inject.Singleton;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author: Md. Moniruzzaman <moni.return@gmail.com>
 * @since: 5/7/2023
 */
@Singleton
public class PdfResponseBuilder implements ResponseBuilder {
    private static final Logger logger = LoggerFactory.getLogger(PdfResponseBuilder.class);

    private AppConfig appConfig;

    public PdfResponseBuilder(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

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
        Map<CharSequence, CharSequence> map = new HashMap<>();
        map.put("content-disposition", "inline; filename=\"%s.pdf\"".formatted(request.getFileName()));
        map.put("Expires", "0");
        map.put("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        map.put("Pragma", "public");
        return map;
    }

//    @Override
//    public Optional<byte[]> convertToFormat(String htmlContent, RenderRequest request) {
//        FileOutputStream outputStream = null;
//        FileInputStream fileInputStream = null;
//        ByteArrayInputStream xhtmlInputStream = null;
//        ByteArrayOutputStream pdfOutputStream = null;
////        String path = "%s/%s/%s/%s/%s.%s".formatted(
////                appConfig.getPublicationBasePath(),
////                request.getUrlType(),
////                request.getCollection(),
////                "pdf",
////                request.getFileName(),
////                "pdf");
////        File outputPdfFile = new File(path);
//        try {
//            Tidy tidy = new Tidy();
//            tidy.setXHTML(true);
//            tidy.setDocType("omit");
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            tidy.parse(new StringReader(htmlContent), baos);
//            byte[] xhtmlBytes = baos.toByteArray();
//            xhtmlInputStream = new ByteArrayInputStream(xhtmlBytes);
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = dbf.newDocumentBuilder();
//            Document inputXmlDoc = builder.parse(xhtmlInputStream);
//
//            ITextRenderer renderer = new ITextRenderer();
//            renderer.setDocument(inputXmlDoc, getAssetHost());
//
//            String documentHtml = getXhtml(htmlContent);
//            ITextRenderer renderer = getRenderer(documentHtml);
////            outputStream = new FileOutputStream(outputPdfFile);
//            renderer.createPDF(outputStream);
////            fileInputStream = new FileInputStream(outputPdfFile);
//            return Optional.of(fileInputStream.readAllBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } finally {
//            IOUtils.closeQuietly(outputStream);
////            IOUtils.closeQuietly(fileInputStream);
//            IOUtils.closeQuietly(xhtmlInputStream);
//            IOUtils.closeQuietly(pdfOutputStream);
//        }
//        return Optional.empty();
//    }
//
//    private String getAssetHost() throws MalformedURLException {
//        return new URL(appConfig.getPublicationBasePath()).getHost();
//    }

    @Override
    public Optional<byte[]> convertToFormat(String htmlContent, RenderRequest request) {
        ByteArrayOutputStream outputStream = null;
        try {
            String documentHtml = getXhtml(htmlContent);
            ITextRenderer renderer = getRenderer(documentHtml);
            outputStream = new ByteArrayOutputStream();
            renderer.createPDF(outputStream);
            return Optional.of(outputStream.toByteArray());
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    private ITextRenderer getRenderer(String documentHtml) {
        ITextRenderer renderer = new ITextRenderer();
        SharedContext sharedContext = renderer.getSharedContext();
        sharedContext.setPrint(true);
        sharedContext.setInteractive(false);
        renderer.setDocumentFromString(documentHtml);
        renderer.layout();
        return renderer;
    }

    private String getXhtml(String htmlContent) {
        Document document = Jsoup.parse(htmlContent);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        document.charset(StandardCharsets.UTF_8);
        logger.debug("###  htmlContent first line: %s".formatted(htmlContent.lines().findFirst().orElse("")));
        String documentHtml = document.html()
                .replaceFirst("<!DOCTYPE html>",
                        "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" " +
                                "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        logger.debug("###  document html: %s".formatted(documentHtml.lines().findFirst().orElse("")));
        return documentHtml;
    }

//    private String getXhtml(String htmlContent) {
//        Tidy tidy = new Tidy();
//        tidy.setXHTML(true);
//        tidy.setDocType("omit");
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        tidy.parse(new StringReader(htmlContent), baos);
//        return baos.toString(StandardCharsets.UTF_8);
//    }

}
