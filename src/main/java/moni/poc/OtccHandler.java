package moni.poc;

import jakarta.inject.Singleton;

@Singleton
public class OtccHandler {

    private AppConfig appConfig;

    public OtccHandler(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public void generate(RendererData rendererData) {
        System.out.println("generate");

        // check cache
        // get renderer
        // render
        // write output
    }
}
