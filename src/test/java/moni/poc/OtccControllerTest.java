package moni.poc;
import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.netty.cookies.NettyCookie;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import io.micronaut.http.client.annotation.*;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class OtccControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void testIndex() {
        HttpRequest<Object> req = HttpRequest.GET("/otcc/archive/cta/printversion/cta_ar_2022-04-15.xml")
                .cookies(Set.of(new NettyCookie("DEV_IBFD_SESSION","unpkWSwzLfyMyHyQHid7kIziLRgwAap7")));
        assertEquals(HttpStatus.OK, client.toBlocking().exchange(req).status());
    }

    @Test
    void testAppConfiguration() {
        Map<String, Object> items = new HashMap<>();
        items.put("app-config.session-cookie-name", "DEV_IBFD_SESSION");
        items.put("app-config.publication-base-path", "D:\\Workshop\\java-project\\otcc\\.idea\\publications");
        items.put("app-config.limaserver-base-url", "http://development7.test.org:9080");
        items.put("app-config.linkresolver-base-url", "https://dev-research.test.org/linkresolver");
        items.put("app-config.linkresolver-use-pos", "true");

        ApplicationContext ctx = ApplicationContext.run(items);
        AppConfig appConfig = ctx.getBean(AppConfig.class);

        assertEquals("DEV_IBFD_SESSION", appConfig.getSessionCookieName());
        assertTrue(appConfig.getLinkresolverUsePost());
        assertEquals("D:\\Workshop\\java-project\\otcc\\.idea\\publications", appConfig.getPublicationBasePath());
        assertEquals("https://dev-research.test.org/linkresolver", appConfig.getLinkresolverBaseUrl());
        assertEquals("http://development7.test.org:9080", appConfig.getLimaserverBaseUrl());

        ctx.close();
    }
}
