package example.poc.renderer;

import example.poc.model.RenderData;
import io.micronaut.http.HttpResponse;
import org.apache.commons.io.IOUtils;
import org.ibfd.regionalxml.RegionalXmlTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public abstract sealed class BaseRenderer permits PdfRenderer, PrintVersionRenderer {
    private static final Logger logger = LoggerFactory.getLogger(BaseRenderer.class);

    private RegionalXmlTransformer transformer;
    private ByteArrayOutputStream transformedResult;
    private RenderData renderData;

    public RegionalXmlTransformer getTransformer() {
        return transformer;
    }

    public ByteArrayOutputStream getTransformedResult() {
        return transformedResult;
    }

    public RenderData getRenderData() {
        return renderData;
    }

    public final HttpResponse<String> render(RenderData renderData) throws Exception {
        this.renderData = renderData;
        logger.info("BaseRenderer render");
        logger.debug("initializing transformer ...");
        initializeTransformer();
        configureTransformer();
        logger.debug("transformer initialization finished.");
        transform();
        return buildResponse();
    }

    protected abstract void configureTransformer();

    protected abstract HttpResponse<String> buildResponse() throws IOException;

    private void transform() throws Exception {
        InputStream srcXmlInputStream = null;
        try {
            srcXmlInputStream = getSrcXmlInputStream();
            this.transformedResult = (ByteArrayOutputStream) transformer.transform(srcXmlInputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            IOUtils.closeQuietly(srcXmlInputStream);
        }
    }

    private void initializeTransformer() throws Exception {
        transformer = new RegionalXmlTransformer();
        transformer.setResultDocumentOutput(false);
        transformer.setExcelVersionPrefix("");
        transformer.setOutputProperty(OutputKeys.METHOD, "html");
    }

    private InputStream getSrcXmlInputStream() throws IOException, TransformerException {
        URLConnection connection = getSrcXmlUrlConnection();
        connection.connect();
        if (connection instanceof HttpURLConnection httpURLConnection) {
            if (httpURLConnection.getResponseCode() == 403) {
                String message = "Fobbiden document - href: %s, with authKey: %s trying to access."
                        .formatted(getRenderData().getSrcXml(), getRenderData().getAuthKey());
                logger.error(message);
                throw new TransformerException(message);
            }
        }
        return connection.getInputStream();
    }

    private URLConnection getSrcXmlUrlConnection() throws IOException {
        URL url = new URL(getRenderData().getSrcXml());
        URLConnection connection = url.openConnection();
        connection.addRequestProperty("Cookie", getRenderData().getAuthKey());
        return connection;
    }
}
