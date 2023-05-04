package example.poc.renderer;

import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class RendererFactory {

    @Singleton
    public BaseRenderer getRenderer(String format) {
        return switch (format) {
            case "printversion" -> new PrintVersionRenderer();
            case "pdf" -> new PdfRenderer();
            default -> throw new IllegalArgumentException("Unsupported format");
        };
    }
}
