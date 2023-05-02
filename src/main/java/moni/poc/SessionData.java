package moni.poc;

import io.micronaut.serde.annotation.Serdeable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Serdeable
public class SessionData {
    private static final String USERNAME_PREFIX = "AUTHUSER:";
    private static final String GREET_PREFIX = "GREET:";
    private static final String SUB_PREFIX = "SUB:";

    private String username;
    private String greet;
    private List<String> subscriptions;

    public SessionData(String sessionDataRaw) {
        System.out.println("SessionData " );
        List<String> parts = sessionDataRaw.lines()
                .map(line -> line.split(";"))
                .flatMap(linePart -> Arrays.stream(linePart).sequential())
                .collect(Collectors.toList());
        this.username = parts.stream()
                .filter(part -> part.startsWith(USERNAME_PREFIX))
                .map(part -> part.substring(USERNAME_PREFIX.length()))
                .findFirst()
                .orElse("");
        this.greet = parts.stream()
                .filter(part -> part.startsWith(GREET_PREFIX))
                .map(part -> part.substring(GREET_PREFIX.length()))
                .findFirst()
                .orElse("");
        this.subscriptions = parts.stream()
                .filter(part -> part.startsWith(SUB_PREFIX))
                .map(part -> part.substring(SUB_PREFIX.length()))
                .collect(Collectors.toList());
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
}
