package moni.poc;

import io.micronaut.serde.annotation.Serdeable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Serdeable
public class SessionData {
    private String username;
    private String greet;
    private List<String> subscriptions;

    public SessionData(String sessionDataRaw) {
        System.out.println("SessionData " );
        this.username = sessionDataRaw;
        this.subscriptions = new ArrayList<>();
//        for (String s : sessionDataRaw.split("\n")) {
//            this.initialize(s);
//        }
    }

    private void initialize(String line){
        String firstPart = line.split(";")[0];
        String[] parts = firstPart.split(":");
        String key = parts[0];
        String value = parts[1];
        System.out.println("key " + key);
        System.out.println("v " + value);
        switch (key) {
            case "AUTHUSER" -> this.username = value;
            case "SUB" -> this.subscriptions.add(value);
            case "GREET" -> this.greet = value;
        }
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
