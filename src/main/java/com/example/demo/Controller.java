package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;

@RestController
public class Controller {

    @PostMapping(value = "/logout_tango")
    public void logoutTango (final HttpServletRequest request) {
        var logoutRequest = request.getParameter("logoutRequest");
        var id = getTextForElement(logoutRequest, "NameID");
        System.out.println("Loging out tango user: " + id);
    }

    @PostMapping(value = "/logout_holm")
    public void logoutHolm (final HttpServletRequest request) {
        var logoutRequest = request.getParameter("logoutRequest");
        var id = getTextForElement(logoutRequest, "NameID");
        System.out.println("Loging out holm user: " + id);
    }

    public static String getTextForElement(final String xmlAsString, final String element) {
        final XMLReader reader = getXmlReader();
        final StringBuilder builder = new StringBuilder();

        final DefaultHandler handler = new DefaultHandler() {

            private boolean foundElement = false;

            @Override
            public void startElement(final String uri, final String localName, final String qName,
                                     final Attributes attributes) {
                if (localName.equals(element)) {
                    this.foundElement = true;
                }
            }

            @Override
            public void endElement(final String uri, final String localName, final String qName) {
                if (localName.equals(element)) {
                    this.foundElement = false;
                }
            }

            @Override
            public void characters(final char[] ch, final int start, final int length) {
                if (this.foundElement) {
                    builder.append(ch, start, length);
                }
            }
        };

        reader.setContentHandler(handler);
        reader.setErrorHandler(handler);

        try {
            reader.parse(new InputSource(new StringReader(xmlAsString)));
        } catch (final Exception e) {
            return null;
        }

        return builder.toString();
    }

    public static XMLReader getXmlReader() {
        try {
            final SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            return factory.newSAXParser().getXMLReader();
        } catch (final Exception e) {
            throw new RuntimeException("Unable to create XMLReader", e);
        }
    }

}
