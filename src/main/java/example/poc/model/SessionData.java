package example.poc.model;

import io.micronaut.serde.annotation.Serdeable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Serdeable
public class SessionData {
    private static final Logger logger = LoggerFactory.getLogger(SessionData.class);

    private static final String USERNAME_PREFIX = "AUTHUSER:";
    private static final String GREET_PREFIX = "GREET:";
    private static final String SUB_PREFIX = "SUB:";

    private final String username;
    private final String greet;
    private final List<String> subscriptions;

    public SessionData(String sessionDataRaw) {
        var parts = sessionDataRaw.lines()
                .map(line -> line.split(";"))
                .flatMap(linePart -> Arrays.stream(linePart).sequential()).toList();
        this.username = parseUsername(parts);
        this.greet = parseGreet(parts);
        this.subscriptions = parseSubscriptions(parts);

        logger.debug("SessionData is constructed: %s".formatted(this));
    }

    public String getUsername() {
        return username;
    }

    public String getGreet() {
        return greet;
    }

    public List<String> getSubscriptions() {
        return subscriptions;
    }

    private String parseUsername(List<String> parts) {
        return parts.stream()
                .filter(part -> part.startsWith(USERNAME_PREFIX))
                .map(part -> part.substring(USERNAME_PREFIX.length()))
                .findFirst()
                .orElse("");
    }

    private String parseGreet(List<String> parts) {
        return parts.stream()
                .filter(part -> part.startsWith(GREET_PREFIX))
                .map(part -> part.substring(GREET_PREFIX.length()))
                .findFirst()
                .orElse("");
    }

    private List<String> parseSubscriptions(List<String> parts) {
        return parts.stream()
                .filter(part -> part.startsWith(SUB_PREFIX))
                .map(part -> part.substring(SUB_PREFIX.length()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "SessionData{" +
                "username='" + username + '\'' +
                ", greet='" + greet + '\'' +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
