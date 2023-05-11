package example.poc.model;

import io.micronaut.core.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record SearchQuery(String fileName, String ext, String sessionData) {
    private static final String SUB_PREFIX = "SUB:";
    private static final String PSUB_PREFIX = "PSUB:";
    private static final String VSUB_PREFIX = "VSUB:";

    public String getSearchQuery() {

        var recordFilterList = getSubRecordFilter();
        recordFilterList.addAll(getPsubRecordFilter());
        recordFilterList.addAll(getVsubRecordFilter());
        var recordFilter = String.join(",", recordFilterList.stream()
                .map(subscription -> "lcf:" + subscription).toList());
        return StringUtils.isEmpty(recordFilter) ?
                "N=0&Nr=xml_source_file:%s.%s".formatted(fileName, ext) :
                "N=0&Nr=AND(xml_source_file:%s,OR(%s))".formatted(fileName, recordFilter);
    }

    private List<String> getSubRecordFilter() {
        List<String> parts = getParts();
        return parts.stream()
                .filter(part -> part.startsWith(SUB_PREFIX))
                .map(part -> part.substring(SUB_PREFIX.length()))
                .map(sub -> "lcf:" + sub)
                .collect(Collectors.toList());
    }

    private List<String> getPsubRecordFilter() {
        List<String> parts = getParts();
        return parts.stream()
                .filter(part -> part.startsWith(PSUB_PREFIX))
                .map(part -> part.substring(PSUB_PREFIX.length()).split("/")[0])
                .collect(Collectors.toList());
    }

    private List<String> getVsubRecordFilter() {
        List<String> parts = getParts();
        return parts.stream()
                .filter(part -> part.startsWith(VSUB_PREFIX))
                .map(part -> part.substring(VSUB_PREFIX.length()).split("/")[0])
                .collect(Collectors.toList();
    }

    private List<String> getParts() {
        return sessionData.lines()
                .map(line -> line.split(";"))
                .flatMap(linePart -> Arrays.stream(linePart).sequential()).toList();
    }
}
