package org.ibfd.otcc.renderer;

import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class RendererFactory {

    private final List<Renderer> renderers;

    public RendererFactory(List<Renderer> renderers) {
        this.renderers = renderers;
    }

    public Renderer getRenderer(String format) {
        return renderers.stream()
                .filter(renderer -> format.equalsIgnoreCase(renderer.getFormatName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported format"));
    }
}
