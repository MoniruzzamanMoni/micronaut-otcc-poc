package moni.poc;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Md. Moniruzzaman <moni.return@gmail.com>
 * @since: 5/2/2023
 */
@MicronautTest
public class SessionDataTest {

    private static Logger logger = LoggerFactory.getLogger(SessionDataTest.class);

    @Test
    void testSessionData() {
        String sessionDataRaw = getSessionDataRaw();
        SessionData sessionData = new SessionData(sessionDataRaw);

        logger.info("Username: " + sessionData.getUsername());
        logger.info("Greet: " + sessionData.getGreet());
        logger.info("Subs: " + sessionData.getSubscriptions());

        assertEquals("test002@ibfd.org", sessionData.getUsername());
        assertEquals("IBFD", sessionData.getGreet());
        assertEquals("kbase", sessionData.getSubscriptions().get(0));
        assertEquals(365, sessionData.getSubscriptions().size());

    }

    private String getSessionDataRaw() {
        return "AUTHUSER:test002@ibfd.org\n" +
                "SUB:kbase;test002@ibfd.org;;username;\n" +
                "SUB:062cl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:2apctp1;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:a16;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:aapcit2;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:aar;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ab;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:acc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:acec;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ald;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:alts;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:amae;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ami;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:apctp1;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:apctp2;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:api;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:appe;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:aptb;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:atac;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:atae;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:atah;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:atg;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:atp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:atr;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:att;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:av;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:beps;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:bifd;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:bitp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:bort;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:bp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:bric;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:bu;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:bv1;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:caa;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:cbc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:cbte;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ccac;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ccc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:cfcl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:cfe;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:cil;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:cit;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:cncirc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:conc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:cot;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:cp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:crit;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ctc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ctrm;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ctti;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:cttl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:da;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:da2012;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:dai;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:db;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:dcftt2;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:dcitp2;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:dcpit1;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:demo;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:demo21;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:dev;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:development;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:dfi;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ditp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:domc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:dreu;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:drtt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:dtc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:dtcc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ebot;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:eche;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ecjd;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ecm;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:eejc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ega;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:eisd;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:elit;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:emc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:esii;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:et;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ete;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:etep;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:eti;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:etl2014;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:etl2015;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:etl2016;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:etl2017;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:etl2018;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:etl2019;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:etl2020;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:etl2021;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:etl2022;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ets;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:eube;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:euft;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:euit;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:eutld;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:eutld2010;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:eutld2011;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:eutld2012;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:eutld2013;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:evd;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:evde;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:evs;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:faim;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ff;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:fmd;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:fsa;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ft;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:g0032ura3;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gaar;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gc21hkg3;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gcc21umof1;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gi;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gi_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gii;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:giii;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:giii_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:giv_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gm;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gm_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gmt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gta;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gta_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gtc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gth;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gth_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gtpep;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gtpep_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gtrb;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gttc2;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gttc2_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gus;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gve;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:gve_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:hac2;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:hitp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:hold;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:hold_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:hrte;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:hst;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ht;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:hta2;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:htc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:htcea;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:iab;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:iatm;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:iatt2013;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ibi;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:icb;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:icta;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:idtc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:idtc2012;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:idtc3;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:idtia;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:idtr;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:iect;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ieu;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ifacahier;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ifu;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ifu_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:igt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:igtt_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:iipe;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:iitl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ikb;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ile;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ioa;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:iop;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ipdl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ita101;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ita102;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ita103;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ita104;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ita105;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ita106;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ita107;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ita108;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ita109;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ita110;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:itaxs;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:itc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:itcg;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:itg;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:itln;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:itp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:itpd;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:itpd2014;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:itpj;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:itpp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:its;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:its_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:itt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ittcer5;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ittcer6;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ittcer7;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ittr;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:itts;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ius;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:iust;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ivg;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ivm;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:lata;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:letl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:lit3;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:lpt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:lret;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:lsa;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:lsco;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:lse;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ma;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ma_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:maie;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:mas;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:mdr;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:mip;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:mitl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:mkit;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:mtit;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:mtt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ndi;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ndtt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:nei;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ngm;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:nic;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:nit;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:nitp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:nji;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:nmtc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:nrt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ntdp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:odp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:oecd2014;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:olit;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:otp1;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:otsf;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:pe;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:pe_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:peva;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:pha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:pigfm;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:pl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:pmc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:pnd;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ppei;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:pt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:rc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:rctt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ritt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:rm;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:rr1;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:rtl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:sal;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:sdt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:sel;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:sitl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:sitl2011;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:soel;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:sp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:sptl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:st;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:stp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:stz;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tar;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tare;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tatp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tatr;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:taum;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:taum2;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:taxmgt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:taxmgt_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tbp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tca;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tcab;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tcat;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tcbp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tccg;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tce;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tcef;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tch;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tciv;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tcp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tdd;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tde;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:te;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:terra;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tes;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tfbi;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tfs;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tfsi;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tib;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tid;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tidt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tif;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tip;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tipa;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tipu;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tis;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:titb;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tiud;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tm;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tmeb;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tmtl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tns;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:toc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpbr;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpbr1;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpc1;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpcv;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpd;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpdr;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpftp2;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpif1;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpig;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpig2;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpint1;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpis1;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpit;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tpro;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tps;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tptp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:trabeps;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:treaties;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:treatycom;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:treatycom_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:trnt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:trsg;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ts;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tsat;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tseu;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tsm;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tta;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttbb;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttcl2013;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttcl2014;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttcl2015;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttcl2016;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttcl2017;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttcl2018;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttcl2019;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttcl2020;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttcl2021;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttclg;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttdl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:tte;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttic;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttna;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:ttt;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:twe;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:uae;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:unm;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:vatd;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:vatww;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:vatww_ha;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:veit;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:vgep;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:vgmc;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:vl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:wbnr;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:wp;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:wtj;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "SUB:wtl;test002@ibfd.org;IBFD/IBFD-InternalIP;ip;1000000\n" +
                "GREET:IBFD\n";
    }
}
