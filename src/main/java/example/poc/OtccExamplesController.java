package example.poc;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OtccExamplesController {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    final List<String> links = Arrays.asList(
            "http://localhost:8080/otcc/archive/cta/printversion/cta_ar_2022-04-15.xml",
            "http://localhost:8080/otcc/archive/cta/printversion/cta_br_2022-07-01.xml",
            "http://localhost:8080/otcc/archive/cta/printversion/cta_fi_2022-07-28.xml",
            "http://localhost:8080/otcc/archive/gtha/printversion/gtha_ar_2022-07-01.xml",
            "http://localhost:8080/otcc/archive/gtha/printversion/gtha_br_2020-11-01.xml",
            "http://localhost:8080/otcc/archive/gtha/printversion/gtha_ca_2021-04-26.xml"
    );
    String body = "<h3>OTCC Example Links</h3><hr>" + links.stream()
            .map(l -> "<a href=\"%s\">%s</a>".formatted(l, l))
            .collect(Collectors.joining("<br>"));

    @Get(produces = MediaType.ALL)
    public HttpResponse<String> getExampleLinks() {
        return HttpResponse.ok(body)
                .contentLength(body.length())
                .contentType(CONTENT_TYPE);
    }

    @Get(uri="/{authKey}", produces = MediaType.ALL)
    public HttpResponse<String> getExampleLinks(@PathVariable String authKey) {
        return HttpResponse.ok(body)
                .contentLength(body.length())
                .contentType(CONTENT_TYPE)
                .header("set-cookie", "DEV_IBFD_SESSION=%s".formatted(authKey));
    }

}