package example.poc.renderer;

import example.poc.model.RenderData;
import org.ibfd.regionalxml.RegionalXmlTransformer;
import org.slf4j.Logger;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public sealed interface Renderer permits PrintVersionRenderer, PdfRenderer {

    String getFormatName();

    Logger getLogger();

    void configureTransformer(RegionalXmlTransformer transformer);

    default String render(RenderData renderData) throws Exception {
        var log = getLogger();
        log.info("BaseRenderer render");
        log.debug("initializing transformer ...");
        var transformer = initializeTransformer(renderData);
        configureTransformer(transformer);
        try(var resultOutputStream = transform(transformer, renderData)) {
            log.debug("transformer initialization finished.");
            return resultOutputStream.toString(StandardCharsets.UTF_8);
        }
    }

    private ByteArrayOutputStream transform(RegionalXmlTransformer transformer, RenderData renderData) throws Exception {
        try (var srcXmlInputStream = getSrcXmlInputStream(renderData)) {
            return (ByteArrayOutputStream) transformer.transform(srcXmlInputStream);
        }
    }

    private RegionalXmlTransformer initializeTransformer(RenderData renderData) throws Exception {
        var transformer = new RegionalXmlTransformer();
        transformer.setResultDocumentOutput(false);
        transformer.setExcelVersionPrefix("");
        transformer.setOutputProperty(OutputKeys.METHOD, "html");
        transformer.setDocidErrNoExit(true);
        transformer.setXmlFileName(renderData.getSrcXmlFileName());
        transformer.setLimaserver(renderData.getLimaServerBaseUrl());
        String csvUids = (renderData.getUids() == null)
                ? "" : String.join(",", renderData.getUids());
        transformer.setCsvUids(csvUids);
        return transformer;
    }

    private InputStream getSrcXmlInputStream(RenderData renderData) throws IOException, TransformerException {
        var log = getLogger();
        var connection = getSrcXmlUrlConnection(renderData);
        connection.connect();
        if (connection instanceof HttpURLConnection httpURLConnection) {
            if (httpURLConnection.getResponseCode() == 403) {
                var message = "Fobbiden document - href: %s, with authKey: %s trying to access."
                        .formatted(renderData.getSrcXml(), renderData.getAuthKey());
                log.error(message);
                throw new TransformerException(message);
            }
        }
        return connection.getInputStream();
    }

    private URLConnection getSrcXmlUrlConnection(RenderData renderData) throws IOException {
        var url = new URL(renderData.getSrcXml());
        var connection = url.openConnection();
        connection.addRequestProperty("Cookie", renderData.getAuthKey());
        return connection;
    }
}
