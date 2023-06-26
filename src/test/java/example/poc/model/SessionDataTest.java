package example.poc.model;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * author: Md. Moniruzzaman <moni.return@gmail.com>
 * since: 5/2/2023
 */
@MicronautTest
public class SessionDataTest {

    private static final Logger logger = LoggerFactory.getLogger(SessionDataTest.class);

    @ParameterizedTest
    @CsvFileSource(resources = "/session-data-test-case.csv")
    void testSessionData(String input, String username, String greet, String subscription, Integer subSize, String psuSub1, String psuSub2, String vSub, String vSub1 ) {
        SessionData sessionData = new SessionData(input);

        assertEquals(username, sessionData.getUsername());
        assertEquals(greet, sessionData.getGreet());
        assertEquals(subscription, sessionData.getSubSubscriptions().get(0));
        assertEquals(subSize, sessionData.getSubSubscriptions().size());
        assertEquals(psuSub1, sessionData.getPsubSubscriptions().get(0));
        assertEquals(psuSub2, sessionData.getPsubSubscriptions().get(1));
        assertEquals(vSub, sessionData.getVsubSubscriptions().get(0));
        assertEquals(vSub1, sessionData.getVsubSubscriptions().get(1));

    }
}
