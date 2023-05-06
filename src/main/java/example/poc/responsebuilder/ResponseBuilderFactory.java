package example.poc.responsebuilder;

import example.poc.renderer.BaseRenderer;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class ResponseBuilderFactory {

    private final List<ResponseBuilder> responseBuilders;

    public ResponseBuilderFactory(List<ResponseBuilder> responseBuilders) {
        this.responseBuilders = responseBuilders;
    }

    public ResponseBuilder getResponseBuilder(String format) {
        return this.responseBuilders.stream()
                .filter(renderer -> format.equalsIgnoreCase(renderer.getFormatName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported format"));
    }
}
