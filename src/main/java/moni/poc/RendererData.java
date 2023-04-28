package moni.poc;

import io.micronaut.runtime.http.scope.RequestScope;

@RequestScope
public class RendererData {

    private AppConfig appConfig;

    public RendererData(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public void initialize(String authKey, String urlPart1, String collection, String format, String fileName, String ext){

    }
}
