package example.poc;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.cookie.Cookie;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/")
public class OtccExamplesController {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private final AppConfig appConfig;

    public OtccExamplesController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Get(uri="/", produces = MediaType.ALL)
    public HttpResponse<String> getExampleLinks() {
        List<String> links = Arrays.asList(
                "http://localhost:8080/otcc/archive/cta/printversion/cta_ar_2022-04-15.xml",
                "http://localhost:8080/otcc/archive/cta/printversion/cta_br_2022-07-01.xml",
                "http://localhost:8080/otcc/archive/cta/printversion/cta_fi_2022-07-28.xml"
        );
        String body = "<h3>OTCC Example Links</h3><hr>" + links.stream()
                .map(l -> "<a href=\"%s\">%s</a>".formatted(l, l))
                .collect(Collectors.joining("<br>"));
        return HttpResponse.ok(body)
                .contentLength(body.length())
                .contentType(CONTENT_TYPE);
    }
}