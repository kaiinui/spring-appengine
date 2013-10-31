package com.googlecode.spring.appengine.taglib;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;

/**
 * @author Marcel Overdijk
 */
public class TldTests {

    @Test
    public void testTldVersion() throws Exception {
        DocumentBuilderFactory docBuilderFactory  = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory .newDocumentBuilder();
        Document doc = docBuilder.parse(new File("src/main/resources/META-INF/gae.tld"));
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        XPathExpression expr = xpath.compile("/taglib/tlib-version/text()");
        String version = (String) expr.evaluate(doc, XPathConstants.STRING);
        assertEquals(System.getProperty("springAppengineVersion"), version);
    }
}
