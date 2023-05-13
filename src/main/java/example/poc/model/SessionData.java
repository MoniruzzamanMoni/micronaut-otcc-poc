package example.poc.model;

import io.micronaut.serde.annotation.Serdeable;

import java.util.Arrays;
import java.util.List;

@Serdeable
public record SessionData(String responseBody) {

    private static final String USERNAME_PREFIX = "AUTHUSER:";
    private static final String GREET_PREFIX = "GREET:";
    private static final String SUB_PREFIX = "SUB:";
    private static final String PSUB_PREFIX = "PSUB:";
    private static final String VSUB_PREFIX = "VSUB:";

    public String getUsername() {
        List<String> parts = getParts();
        return parts.stream()
                .filter(part -> part.startsWith(USERNAME_PREFIX))
                .map(part -> part.substring(USERNAME_PREFIX.length()))
                .findFirst()
                .orElse("");
    }

    public String getGreet() {
        List<String> parts = getParts();
        return parts.stream()
                .filter(part -> part.startsWith(GREET_PREFIX))
                .map(part -> part.substring(GREET_PREFIX.length()))
                .findFirst()
                .orElse("");
    }

    public List<String> getSubSubscriptions() {
        List<String> parts = getParts();
        return parts.stream()
                .filter(part -> part.startsWith(SUB_PREFIX))
                .map(part -> part.substring(SUB_PREFIX.length()))
                .toList();
    }

    public List<String> getPsubSubscriptions() {
        List<String> parts = getParts();
        return parts.stream()
                .filter(part -> part.startsWith(PSUB_PREFIX))
                .map(part -> part.substring(PSUB_PREFIX.length()).split("/")[0])
                .toList();
    }

    public List<String> getVsubSubscriptions() {
        List<String> parts = getParts();
        return parts.stream()
                .filter(part -> part.startsWith(VSUB_PREFIX))
                .map(part -> part.substring(VSUB_PREFIX.length()).split("/")[0])
                .toList();
    }

    private List<String> getParts() {
        return responseBody.lines()
                .map(line -> line.split(";"))
                .flatMap(linePart -> Arrays.stream(linePart).sequential()).toList();
    }

}
