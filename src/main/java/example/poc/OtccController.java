package example.poc;

import example.poc.model.RenderRequest;
import example.poc.responsebuilder.ResponseBuilderFactory;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.RequestBean;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Controller
public class OtccController {

    private final OtccHandler otccHandler;
    private final ResponseBuilderFactory responseBuilderFactory;

    public OtccController(OtccHandler otccHandler, ResponseBuilderFactory responseBuilderFactory) {
        this.otccHandler = otccHandler;
        this.responseBuilderFactory = responseBuilderFactory;
    }

    @Get(uri="/otcc/{urlType}/{collection}/{format}/{fileName}.{ext}", produces = MediaType.ALL)
    public HttpResponse<byte[]> render(@Valid @RequestBean RenderRequest request) throws Exception{
        var output = otccHandler.handle(request);
        return responseBuilderFactory.getResponseBuilder(request.getFormat())
                .buildResponse(output, request);
    }


    // For example links
    @Get(produces = MediaType.ALL)
    public HttpResponse<String> getExampleLinks() {
        var docNames = Arrays.asList(
                "cta_ar_2022-04-15.xml",
                "cta_br_2022-07-01.xml",
                "cta_fi_2022-07-28.xml",
                "gtha_ar_2022-07-01.xml",
                "gtha_br_2020-11-01.xml",
                "gtha_ca_2021-04-26.xml"
                );

        var links = docNames.stream()
                .map(d -> Arrays.asList(
                                "http://localhost:8080/otcc/archive/" + d.split("_")[0] + "/printversion/" + d,
                                "http://localhost:8080/otcc/archive/" + d.split("_")[0] + "/pdf/" + d))
                .flatMap(Collection::stream)
                .toList();

        var body = "<h3>OTCC Example Links</h3><hr>" + links.stream()
                .map(l -> "<a href=\"%s\">%s</a>".formatted(l, l))
                .collect(Collectors.joining("<br>"));
        return HttpResponse.ok(body)
                .contentLength(body.length())
                .contentType("text/html; charset=UTF-8");
    }
}