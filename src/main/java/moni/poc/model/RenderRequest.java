package moni.poc.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.annotation.CookieValue;
import io.micronaut.http.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Md. Moniruzzaman <moni.return@gmail.com>
 * @since: 4/30/2023
 */
@Introspected
public class RenderRequest {
    private static final Logger logger = LoggerFactory.getLogger(RenderRequest.class);

    @CookieValue("DEV_IBFD_SESSION")
    private String authKey;

    @PathVariable("urlType")
    private String urlType;

    @PathVariable("collection")
    private String collection;

    @PathVariable("format")
    private String format;

    @PathVariable("fileName")
    private String fileName;

    @PathVariable("ext")
    private String ext;

    public RenderRequest(String authKey,
                         String urlType,
                         String collection,
                         String format,
                         String fileName,
                         String ext) {
        this.authKey = authKey;
        this.urlType = urlType;
        this.collection = collection;
        this.format = format;
        this.fileName = fileName;
        this.ext = ext;
        logger.debug("RenderRequest is constructed: %s".formatted(this));
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getUrlType() {
        return urlType;
    }

    public String getCollection() {
        return collection;
    }

    public String getFormat() {
        return format;
    }

    public String getFileName() {
        return fileName;
    }

    public String getExt() {
        return ext;
    }

    @Override
    public String toString() {
        return "RenderRequestBean{" +
                "authKey='" + authKey + '\'' +
                ", urlType='" + urlType + '\'' +
                ", collection='" + collection + '\'' +
                ", format='" + format + '\'' +
                ", fileName='" + fileName + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }
}
