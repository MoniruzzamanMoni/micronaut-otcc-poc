package example.poc;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("app-config")
public class AppConfig {
    private String sessionCookieName;
    private String publicationBasePath;
    private String sessionManagerUrl;
    private String limaserverBaseUrl;
    private String linkresolverBaseUrl;
    private boolean linkresolverUsePost;
    private String regionalPdfXslUrl;

    public AppConfig() {
    }

    public String getSessionCookieName() {
        return sessionCookieName;
    }

    public void setSessionCookieName(String sessionCookieName) {
        this.sessionCookieName = sessionCookieName;
    }

    public String getPublicationBasePath() {
        return publicationBasePath;
    }

    public void setPublicationBasePath(String publicationBasePath) {
        this.publicationBasePath = publicationBasePath;
    }

    public String getSessionManagerUrl() {
        return sessionManagerUrl;
    }

    public void setSessionManagerUrl(String sessionManagerUrl) {
        this.sessionManagerUrl = sessionManagerUrl;
    }

    public String getLimaserverBaseUrl() {
        return limaserverBaseUrl;
    }

    public void setLimaserverBaseUrl(String limaserverBaseUrl) {
        this.limaserverBaseUrl = limaserverBaseUrl;
    }

    public String getLinkresolverBaseUrl() {
        return linkresolverBaseUrl;
    }

    public void setLinkresolverBaseUrl(String linkresolverBaseUrl) {
        this.linkresolverBaseUrl = linkresolverBaseUrl;
    }

    public boolean getLinkresolverUsePost() {
        return linkresolverUsePost;
    }

    public void setLinkresolverUsePost(boolean linkresolverUsePost) {
        this.linkresolverUsePost = linkresolverUsePost;
    }

    public String getRegionalPdfXslUrl() {
        return regionalPdfXslUrl;
    }

    public void setRegionalPdfXslUrl(String regionalPdfXslUrl) {
        this.regionalPdfXslUrl = regionalPdfXslUrl;
    }
}
