package moni.poc;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.netty.cookies.NettyCookie;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import io.micronaut.http.client.annotation.*;
import jakarta.inject.Inject;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class OtccControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void testIndex() throws Exception {
        HttpRequest req = HttpRequest.GET("/otcc/archive/cta/printversion/cta_ar_2022-04-15.xml")
                .cookies(Set.of(new NettyCookie("DEV_IBFD_SESSION","unpkWSwzLfyMyHyQHid7kIziLRgwAap7")));
        assertEquals(HttpStatus.OK, client.toBlocking().exchange(req).status());
    }
}
