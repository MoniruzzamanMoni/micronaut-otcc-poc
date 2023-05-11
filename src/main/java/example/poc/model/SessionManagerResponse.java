package example.poc.model;

import io.micronaut.serde.annotation.Serdeable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@Serdeable
<<<<<<< Updated upstream
public class SessionData {
    private static final Logger logger = LoggerFactory.getLogger(SessionData.class);

    private static final String USERNAME_PREFIX = "AUTHUSER:";
    private static final String GREET_PREFIX = "GREET:";
    private static final String SUB_PREFIX = "SUB:";
    private static final String PSUB_PREFIX = "PSUB:";
    private static final String VSUB_PREFIX = "VSUB:";

    private final String username;
    private final String greet;
    private final Map<String, String> recordFilter;
    private final List<String> subscriptions;

    public SessionData(String sessionDataRaw) {
        var parts = sessionDataRaw.lines()
                .map(line -> line.split(";"))
                .flatMap(linePart -> Arrays.stream(linePart).sequential()).toList();
        this.username = parseUsername(parts);
        this.greet = parseGreet(parts);
        this.subscriptions = parseSubscriptions(parts);
        this.recordFilter = parseRecordFilter(parts);

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

    public Map<String, String> getRecordFilter() {
        return recordFilter;
    }

=======
public record SessionManagerResponse(String sessionData) {
//    private static final Logger logger = LoggerFactory.getLogger(SessionData.class);
//
    private static final String USERNAME_PREFIX = "AUTHUSER:";
    private static final String GREET_PREFIX = "GREET:";
//    private static final String SUB_PREFIX = "SUB:";
//    private static final String PSUB_PREFIX = "PSUB:";
//    private static final String VSUB_PREFIX = "VSUB:";
//
//    private final String username;
//    private final String greet;
//    private final Map<String, String> recordFilter;
//    private final List<String> subscriptions;
//
//    public SessionData(String sessionDataRaw) {
//        var parts = sessionDataRaw.lines()
//                .map(line -> line.split(";"))
//                .flatMap(linePart -> Arrays.stream(linePart).sequential()).toList();
//        this.username = parseUsername(parts);
//        this.greet = parseGreet(parts);
//        this.subscriptions = parseSubscriptions(parts);
//        this.recordFilter = parseRecordFilter(parts);
//
//        logger.debug("SessionData is constructed: %s".formatted(this));
//    }
//
    public String getUsername() {
        var parts = getParts();
        return parts.stream()
                .filter(part -> part.startsWith(USERNAME_PREFIX))
                .map(part -> part.substring(USERNAME_PREFIX.length()))
                .findFirst()
                .orElse("");
    }

    public String getGreet() {
        var parts = getParts();
        return parts.stream()
                .filter(part -> part.startsWith(GREET_PREFIX))
                .map(part -> part.substring(GREET_PREFIX.length()))
                .findFirst()
                .orElse("");
    }

    private List<String> getParts() {
        return sessionData.lines()
                .map(line -> line.split(";"))
                .flatMap(linePart -> Arrays.stream(linePart).sequential()).toList();
    }
//
//    public List<String> getSubscriptions() {
//        return subscriptions;
//    }
//
//    public Map<String, String> getRecordFilter() {
//        return recordFilter;
//    }
//
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

    private List<String> parseSubscriptions(List<String> parts) {
        return parts.stream()
                .filter(part -> part.startsWith(SUB_PREFIX))
                .map(part -> part.substring(SUB_PREFIX.length()))
                .collect(Collectors.toList());
    }

    private Map<String, String> parseRecordFilter(List<String> parts) {
        Map<String, String> subRecordFilter = getSubRecordFilter(parts);
        subRecordFilter.putAll(getPsubRecordFilter(parts));
        subRecordFilter.putAll(getVsubRecordFilter(parts));
        return subRecordFilter;
    }

    private Map<String, String> getSubRecordFilter(List<String> parts) {
        return parts.stream()
                .filter(part -> part.startsWith(SUB_PREFIX))
                .map(part -> part.substring(SUB_PREFIX.length()))
                .collect(Collectors.toMap(p -> p, p -> "lcf:" + p));
    }

    private Map<String, String> getPsubRecordFilter(List<String> parts) {
        return parts.stream()
                .filter(part -> part.startsWith(PSUB_PREFIX))
                .map(part -> part.substring(PSUB_PREFIX.length()).split("/"))
                .collect(Collectors.toMap(f -> f[1], f -> f[0]));
    }

    private Map<String, String> getVsubRecordFilter(List<String> parts) {
        return parts.stream()
                .filter(part -> part.startsWith(VSUB_PREFIX))
                .map(part -> part.substring(VSUB_PREFIX.length()).split("/"))
                .collect(Collectors.toMap(f -> f[1], f -> f[0]));
    }

    private String getRecordFilterKey(String part) {
        return switch (part) {
            case part ->
        }
    }

    private String getRecordFilterValue(String part) {

    }

    @Override
    public String toString() {
        return "SessionData{" +
                "username='" + username + '\'' +
                ", greet='" + greet + '\'' +
                ", subscriptions=" + subscriptions +
                '}';
    }
=======
//
//    private List<String> parseSubscriptions(List<String> parts) {
//        return parts.stream()
//                .filter(part -> part.startsWith(SUB_PREFIX))
//                .map(part -> part.substring(SUB_PREFIX.length()))
//                .collect(Collectors.toList());
//    }
//
//    private Map<String, String> parseRecordFilter(List<String> parts) {
//        Map<String, String> subRecordFilter = getSubRecordFilter(parts);
//        subRecordFilter.putAll(getPsubRecordFilter(parts));
//        subRecordFilter.putAll(getVsubRecordFilter(parts));
//        return subRecordFilter;
//    }
//
//    private Map<String, String> getSubRecordFilter(List<String> parts) {
//        return parts.stream()
//                .filter(part -> part.startsWith(SUB_PREFIX))
//                .map(part -> part.substring(SUB_PREFIX.length()))
//                .collect(Collectors.toMap(p -> p, p -> "lcf:" + p));
//    }
//
//    private Map<String, String> getPsubRecordFilter(List<String> parts) {
//        return parts.stream()
//                .filter(part -> part.startsWith(PSUB_PREFIX))
//                .map(part -> part.substring(PSUB_PREFIX.length()).split("/"))
//                .collect(Collectors.toMap(f -> f[1], f -> f[0]));
//    }
//
//    private Map<String, String> getVsubRecordFilter(List<String> parts) {
//        return parts.stream()
//                .filter(part -> part.startsWith(VSUB_PREFIX))
//                .map(part -> part.substring(VSUB_PREFIX.length()).split("/"))
//                .collect(Collectors.toMap(f -> f[1], f -> f[0]));
//    }

//    @Override
//    public String toString() {
//        return "SessionData{" +
//                "username='" + username + '\'' +
//                ", greet='" + greet + '\'' +
//                ", subscriptions=" + subscriptions +
//                '}';
//    }
>>>>>>> Stashed changes
}
