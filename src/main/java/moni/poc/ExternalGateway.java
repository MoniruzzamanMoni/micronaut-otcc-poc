package moni.poc;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Singleton;
import moni.poc.model.LinkResolverData;
import moni.poc.model.LinkResolverRequest;
import moni.poc.model.SessionData;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Singleton
public class ExternalGateway {
    private final AppConfig appConfig;
    private final HttpClient sessionMangerClient;
    private final HttpClient limaServerClient;
    private final HttpClient linkResolverClient;

    public ExternalGateway(AppConfig appConfig,
                           @Client("sessionManagerClient") HttpClient sessionMangerClient,
                           @Client("limaServerClient") HttpClient limaServerClient,
                           @Client("linkResolverClient") HttpClient linkResolverClient) {
        this.appConfig = appConfig;
        this.sessionMangerClient = sessionMangerClient;
        this.limaServerClient = limaServerClient;
        this.linkResolverClient = linkResolverClient;
    }

    public SessionData getSessionData(String authKey) throws MalformedURLException {
        URL url = new URL(appConfig.getSessionManagerUrl());
        String body = "key=%s&action=read".formatted(authKey);
        HttpRequest<?> request = HttpRequest.POST(url.getPath(), body)
                .header(HttpHeaders.ACCEPT, "*/*")
                .header(HttpHeaders.HOST, url.getHost())
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(body.length()));

        Mono<String> responseBody = Mono.from(sessionMangerClient.retrieve(request, String.class));
        return new SessionData(responseBody.blockOptional().orElse(""));
    }

    public LinkResolverData getLinkResolverData(RenderRequestBean request, LinkResolverRequest linkResolverRequest)
            throws IOException {
        URL url = new URL(appConfig.getLinkresolverBaseUrl());
        String cookie = "%s=%s".formatted(appConfig.getSessionCookieName(), request.getAuthKey());
        HttpRequest<?> httpRequest = HttpRequest.POST(url.getPath(), linkResolverRequest)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.COOKIE, cookie);

        Mono<String> responseBody = Mono.from(linkResolverClient.retrieve(httpRequest, String.class));
        String content = responseBody.blockOptional().orElse("");

        return new LinkResolverData(content);
    }

    private String getVal() {
        return """
                {
                    "search": "N=0&Nr=AND(xml_source_file:cta_ar_2022-04-15.xml,OR(lcf:kbase,lcf:062cl,lcf:2apctp1,lcf:a16,lcf:aapcit2,lcf:aar,lcf:ab,lcf:acc,lcf:acec,lcf:ald,lcf:alts,lcf:amae,lcf:ami,lcf:apctp1,lcf:apctp2,lcf:api,lcf:appe,lcf:aptb,lcf:atac,lcf:atae,lcf:atah,lcf:atg,lcf:atp,lcf:atr,lcf:att,lcf:av,lcf:beps,lcf:bifd,lcf:bitp,lcf:bort,lcf:bp,lcf:bric,lcf:bu,lcf:bv1,lcf:caa,lcf:cbc,lcf:cbte,lcf:ccac,lcf:ccc,lcf:cfcl,lcf:cfe,lcf:cil,lcf:cit,lcf:cncirc,lcf:conc,lcf:cot,lcf:cp,lcf:crit,lcf:ctc,lcf:ctrm,lcf:ctti,lcf:cttl,lcf:da,lcf:da2012,lcf:dai,lcf:db,lcf:dcftt2,lcf:dcitp2,lcf:dcpit1,lcf:demo,lcf:demo21,lcf:dev,lcf:development,lcf:dfi,lcf:ditp,lcf:domc,lcf:dreu,lcf:drtt,lcf:dtc,lcf:dtcc,lcf:ebot,lcf:eche,lcf:ecjd,lcf:ecm,lcf:eejc,lcf:ega,lcf:eisd,lcf:elit,lcf:emc,lcf:esii,lcf:et,lcf:ete,lcf:etep,lcf:eti,lcf:etl2014,lcf:etl2015,lcf:etl2016,lcf:etl2017,lcf:etl2018,lcf:etl2019,lcf:etl2020,lcf:etl2021,lcf:etl2022,lcf:ets,lcf:eube,lcf:euft,lcf:euit,lcf:eutld,lcf:eutld2010,lcf:eutld2011,lcf:eutld2012,lcf:eutld2013,lcf:evd,lcf:evde,lcf:evs,lcf:faim,lcf:ff,lcf:fmd,lcf:fsa,lcf:ft,lcf:g0032ura3,lcf:gaar,lcf:gc21hkg3,lcf:gcc21umof1,lcf:gi,lcf:gi_ha,lcf:gii,lcf:giii,lcf:giii_ha,lcf:giv_ha,lcf:gm,lcf:gm_ha,lcf:gmt,lcf:gta,lcf:gta_ha,lcf:gtc,lcf:gth,lcf:gth_ha,lcf:gtpep,lcf:gtpep_ha,lcf:gtrb,lcf:gttc2,lcf:gttc2_ha,lcf:gus,lcf:gve,lcf:gve_ha,lcf:hac2,lcf:hitp,lcf:hold,lcf:hold_ha,lcf:hrte,lcf:hst,lcf:ht,lcf:hta2,lcf:htc,lcf:htcea,lcf:iab,lcf:iatm,lcf:iatt2013,lcf:ibi,lcf:icb,lcf:icta,lcf:idtc,lcf:idtc2012,lcf:idtc3,lcf:idtia,lcf:idtr,lcf:iect,lcf:ieu,lcf:ifacahier,lcf:ifu,lcf:ifu_ha,lcf:igt,lcf:igtt_ha,lcf:iipe,lcf:iitl,lcf:ikb,lcf:ile,lcf:ioa,lcf:iop,lcf:ipdl,lcf:ita101,lcf:ita102,lcf:ita103,lcf:ita104,lcf:ita105,lcf:ita106,lcf:ita107,lcf:ita108,lcf:ita109,lcf:ita110,lcf:itaxs,lcf:itc,lcf:itcg,lcf:itg,lcf:itln,lcf:itp,lcf:itpd,lcf:itpd2014,lcf:itpj,lcf:itpp,lcf:its,lcf:its_ha,lcf:itt,lcf:ittcer5,lcf:ittcer6,lcf:ittcer7,lcf:ittr,lcf:itts,lcf:ius,lcf:iust,lcf:ivg,lcf:ivm,lcf:lata,lcf:letl,lcf:lit3,lcf:lpt,lcf:lret,lcf:lsa,lcf:lsco,lcf:lse,lcf:ma,lcf:ma_ha,lcf:maie,lcf:mas,lcf:mdr,lcf:mip,lcf:mitl,lcf:mkit,lcf:mtit,lcf:mtt,lcf:ndi,lcf:ndtt,lcf:nei,lcf:ngm,lcf:nic,lcf:nit,lcf:nitp,lcf:nji,lcf:nmtc,lcf:nrt,lcf:ntdp,lcf:odp,lcf:oecd2014,lcf:olit,lcf:otp1,lcf:otsf,lcf:pe,lcf:pe_ha,lcf:peva,lcf:pha,lcf:pigfm,lcf:pl,lcf:pmc,lcf:pnd,lcf:ppei,lcf:pt,lcf:rc,lcf:rctt,lcf:ritt,lcf:rm,lcf:rr1,lcf:rtl,lcf:sal,lcf:sdt,lcf:sel,lcf:sitl,lcf:sitl2011,lcf:soel,lcf:sp,lcf:sptl,lcf:st,lcf:stp,lcf:stz,lcf:tar,lcf:tare,lcf:tatp,lcf:tatr,lcf:taum,lcf:taum2,lcf:taxmgt,lcf:taxmgt_ha,lcf:tbp,lcf:tc,lcf:tca,lcf:tcab,lcf:tcat,lcf:tcbp,lcf:tccg,lcf:tce,lcf:tcef,lcf:tch,lcf:tciv,lcf:tcp,lcf:tdd,lcf:tde,lcf:te,lcf:terra,lcf:tes,lcf:tfbi,lcf:tfs,lcf:tfsi,lcf:tib,lcf:tid,lcf:tidt,lcf:tif,lcf:tip,lcf:tipa,lcf:tipu,lcf:tis,lcf:titb,lcf:tiud,lcf:tm,lcf:tmeb,lcf:tmtl,lcf:tns,lcf:toc,lcf:tpbr,lcf:tpbr1,lcf:tpc1,lcf:tpcv,lcf:tpd,lcf:tpdr,lcf:tpftp2,lcf:tpif1,lcf:tpig,lcf:tpig2,lcf:tpint1,lcf:tpis1,lcf:tpit,lcf:tpro,lcf:tps,lcf:tptp,lcf:trabeps,lcf:treaties,lcf:treatycom,lcf:treatycom_ha,lcf:trnt,lcf:trsg,lcf:ts,lcf:tsat,lcf:tseu,lcf:tsm,lcf:tta,lcf:ttbb,lcf:ttcl2013,lcf:ttcl2014,lcf:ttcl2015,lcf:ttcl2016,lcf:ttcl2017,lcf:ttcl2018,lcf:ttcl2019,lcf:ttcl2020,lcf:ttcl2021,lcf:ttclg,lcf:ttdl,lcf:tte,lcf:ttic,lcf:ttna,lcf:ttp,lcf:ttt,lcf:tvc,lcf:twe,lcf:uae,lcf:unm,lcf:vatd,lcf:vatww,lcf:vatww_ha,lcf:veit,lcf:vgep,lcf:vgmc,lcf:vl,lcf:wbnr,lcf:wp,lcf:wtj,lcf:wtl))",
                    "properties": "uid",
                    "maxresults": "50"
                }
                """;
    }

}
