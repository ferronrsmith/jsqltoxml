package com.uwi.cli;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.StandardErrorStreamLog;

import static org.junit.Assert.assertTrue;

public class EngineParamTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
    @Rule
    public final StandardErrorStreamLog log = new StandardErrorStreamLog();

    @Before
    public void before() {
        log.clear();
    }

    private void test(String test[], String assertion) {
        exit.expectSystemExit();
        Engine.main(test);
        assertTrue(log.getLog().contains(assertion));
        System.out.println(log.getLog());
    }

    @Test
    public void testInvalidXML() throws Exception {
        test(
                new String[]{"-x", "xml/countries.xml", "-s", "select * from"},
                "Error parsing : select * from\nInvalid SQL Statement ..");
    }

    @Test
    public void testNoSql() throws Exception {
        test(
                new String[]{"-x", "xml/countries.xml"}, "Option \"-sql (-s)\" is required");
    }

    @Test
    public void testNoXml() throws Exception {
        test(
                new String[]{"-s", "select * from country"}, "Option \"-xml (-x)\" is required");
    }

}
