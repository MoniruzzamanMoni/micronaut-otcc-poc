package moni.poc;

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

    public String getSessionCookieName() {
        return sessionCookieName;
    }

    public String getPublicationBasePath() {
        return publicationBasePath;
    }

    public String getSessionManagerUrl() {
        return sessionManagerUrl;
    }

    public String getLimaserverBaseUrl() {
        return limaserverBaseUrl;
    }

    public String getLinkresolverBaseUrl() {
        return linkresolverBaseUrl;
    }

    public boolean getLinkresolverUsePost() {
        return linkresolverUsePost;
    }

    public String getRegionalPdfXslUrl() {
        return regionalPdfXslUrl;
    }
}
