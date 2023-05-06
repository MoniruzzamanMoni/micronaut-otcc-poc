package example.poc.renderer;

import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class RendererFactory {

    private final List<BaseRenderer> renderers;

    public RendererFactory(List<BaseRenderer> renderers) {
        this.renderers = renderers;
    }

    public BaseRenderer getRenderer(String format) {
        return renderers.stream()
                .filter(renderer -> format.equalsIgnoreCase(renderer.getFormatName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported format"));
    }
}
