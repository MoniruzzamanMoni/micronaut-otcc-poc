package moni.poc;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.annotation.CookieValue;
import io.micronaut.http.annotation.PathVariable;

/**
 * @author: Md. Moniruzzaman <moni.return@gmail.com>
 * @since: 4/30/2023
 */
@Introspected
public class RenderRequestBean {

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

    public RenderRequestBean(String authKey,
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
}
