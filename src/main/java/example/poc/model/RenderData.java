package example.poc.model;

import io.micronaut.core.util.StringUtils;
import example.poc.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RenderData {
    private static final Logger logger = LoggerFactory.getLogger(RenderData.class);
    private final String srcXml;
    private final String serverHost;
    private final String assetHost;
    private final List<String> uids;
    private final String srcXmlFileName;
    private final String srcXmlBaseName;
    private final String authKey;
    private final String username;
    private final String userCompanyName;
    private final boolean userLoggedIn;

    public RenderData(AppConfig appConfig, RenderRequest request, SessionData sessionData, LinkResolverData linkResolverData) {
        this.srcXml = "file:///%s/%s/%s/%s/%s.%s".formatted(
                appConfig.getPublicationBasePath(),
                request.getUrlType(),
                request.getCollection(),
                "xml",
                request.getFileName(),
                "xml");
        this.serverHost = this.srcXml.replaceAll("^(http[s]?://[^/]+).*$", "$1/");
        this.assetHost = this.serverHost;
        this.uids = linkResolverData.getUids();
        this.srcXmlBaseName = request.getFileName();
        this.srcXmlFileName = "%s.%s".formatted(this.srcXmlBaseName, request.getExt());
        this.authKey = request.getAuthKey();
        this.username = sessionData.getUsername();
        this.userCompanyName = sessionData.getGreet();
        this.userLoggedIn = StringUtils.isNotEmpty(this.username);

        logger.debug("RenderData is constructed: %s".formatted(this));
    }

    public String getSrcXml() {
        return srcXml;
    }

    public String getServerHost() {
        return serverHost;
    }

    public String getAssetHost() {
        return assetHost;
    }

    public List<String> getUids() {
        return uids;
    }

    public String getSrcXmlFileName() {
        return srcXmlFileName;
    }

    public String getSrcXmlBaseName() {
        return srcXmlBaseName;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getUsername() {
        return username;
    }

    public String getUserCompanyName() {
        return userCompanyName;
    }

    public boolean isUserLoggedIn() {
        return userLoggedIn;
    }

    @Override
    public String toString() {
        return "RenderData{" +
                "srcXml='" + srcXml + '\'' +
                ", serverHost='" + serverHost + '\'' +
                ", assetHost='" + assetHost + '\'' +
                ", uids=" + uids +
                ", srcXmlFileName='" + srcXmlFileName + '\'' +
                ", srcXmlBaseName='" + srcXmlBaseName + '\'' +
                ", authKey='" + authKey + '\'' +
                ", username='" + username + '\'' +
                ", userCompanyName='" + userCompanyName + '\'' +
                ", userLoggedIn=" + userLoggedIn +
                '}';
    }
}
