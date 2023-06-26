package example.poc.model;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@MicronautTest
public class LinkResolverDataTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/link-resolver-test-case.csv")
    void linkResolverDataTest(String input, String output, Integer uIdSize) {
        LinkResolverData linkResolverData = new LinkResolverData(input);
        List<String> uids = linkResolverData.getUids();
        assertEquals(output, uids.get(0));
        assertEquals(uIdSize, uids.size());
    }
}
