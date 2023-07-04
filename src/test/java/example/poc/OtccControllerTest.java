package example.poc;

import io.micronaut.context.ApplicationContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class OtccControllerTest {

    @Test
    void testAppConfiguration() {
        Map<String, Object> items = new HashMap<>();
        items.put("app-config.session-cookie-name", "DEV_IBFD_SESSION");
        items.put("app-config.publication-base-path", "D:\\Workshop\\java-project\\otcc\\.idea\\publications");
        items.put("app-config.limaserver-base-url", "http://development7.test.org:9080");
        items.put("app-config.linkresolver-base-url", "https://dev-research.test.org/linkresolver");

        ApplicationContext ctx = ApplicationContext.run(items);
        AppConfig appConfig = ctx.getBean(AppConfig.class);

        assertEquals("DEV_IBFD_SESSION", appConfig.getSessionCookieName());
        assertEquals("D:\\Workshop\\java-project\\otcc\\.idea\\publications", appConfig.getPublicationBasePath());
        assertEquals("https://dev-research.test.org/linkresolver", appConfig.getLinkresolverBaseUrl());
        assertEquals("http://development7.test.org:9080", appConfig.getLimaserverBaseUrl());

        ctx.close();
    }

}
