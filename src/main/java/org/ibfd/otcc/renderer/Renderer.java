package org.ibfd.otcc.renderer;

import org.ibfd.otcc.exception.RegionalXmlTransformerException;
import org.ibfd.otcc.exception.RenderDataRequestException;
import org.ibfd.otcc.exception.RendererException;
import org.ibfd.otcc.model.RenderData;
import org.ibfd.regionalxml.RegionalXmlTransformer;
import org.slf4j.Logger;

import javax.xml.transform.OutputKeys;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
            return getOutput(transformer, srcXmlInputStream);
        }
    }

    private RegionalXmlTransformer initializeTransformer(RenderData renderData) throws Exception {
        RegionalXmlTransformer transformer = getRegionalXmlTransformer();
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

    private InputStream getSrcXmlInputStream(RenderData renderData) throws Exception {
        var log = getLogger();
        var connection = getSrcXmlUrlConnection(renderData);
        openConnection(connection);
        if (connection instanceof HttpURLConnection httpURLConnection) {
            if (httpURLConnection.getResponseCode() == 403) {
                var message = "Fobbiden document - href: %s, with authKey: %s trying to access."
                        .formatted(renderData.getSrcXml(), renderData.getAuthKey());
                log.error(message);
                throw new RendererException(message);
            }
        }
        return getInputStream(connection);
    }

    private URLConnection getSrcXmlUrlConnection(RenderData renderData) throws Exception {
        URL url = getSrcXmlUrl(renderData);
        URLConnection connection = getUrlConnection(url);
        connection.addRequestProperty("Cookie", renderData.getAuthKey());
        return connection;
    }

    private ByteArrayOutputStream getOutput(RegionalXmlTransformer transformer, InputStream srcXmlInputStream) throws RegionalXmlTransformerException {
        try {
            return (ByteArrayOutputStream) transformer.transform(srcXmlInputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RegionalXmlTransformerException("Can not transform.");
        }
    }

    private RegionalXmlTransformer getRegionalXmlTransformer() throws Exception {
        try {
            return new RegionalXmlTransformer();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RegionalXmlTransformerException("Regional XML Transformer is not created.");
        }
    }

    private void openConnection(URLConnection connection) throws RendererException {
        try {
            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RendererException("SrcXml url connection is not connected.", e);
        }
    }

    private InputStream getInputStream(URLConnection connection) throws RendererException {
        try {
            return connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RendererException("Can not get input stream from source xml connection.", e);
        }
    }

    private URLConnection getUrlConnection(URL url) throws RendererException {
        try {
            return url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RendererException("SrcXml url connection is not open.", e);
        }
    }

    private URL getSrcXmlUrl(RenderData renderData) throws RenderDataRequestException {
        try {
            return new URL(renderData.getSrcXml());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RenderDataRequestException("Source xml url is not in good form.", e);
        }
    }
}
