//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.StringReader;
import java.util.*;

public class TestParserUtils extends InformaTestCase {

    public TestParserUtils(String name) {
        super("TestParserUtils", name);
    }

    public void testParseDateFmt() {
        String strdate;
        Date resDate;
        Calendar expDate;

        strdate = "Wed, 07 Aug 2002 00:32:05 GMT";
        resDate = ParserUtils.getDate(strdate);
        expDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        expDate.set(2002, Calendar.AUGUST, 7, 0, 32, 5);
        expDate.set(Calendar.MILLISECOND, 0);
        assertEquals(expDate.getTime(), resDate);

        strdate = "Tue, 21 Dec 2004 23:02 +0100";
        resDate = ParserUtils.getDate(strdate);
        expDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        expDate.set(2004, Calendar.DECEMBER, 21, 22, 02, 00);
        expDate.set(Calendar.MILLISECOND, 0);
        assertEquals(expDate.getTime(), resDate);

        strdate = "2002-08-07T15:32:05-0500 ";
        resDate = ParserUtils.getDate(strdate);
        expDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        expDate.set(2002, Calendar.AUGUST, 7, 20, 32, 5);
        expDate.set(Calendar.MILLISECOND, 0);
        assertEquals(expDate.getTime(), resDate);

        strdate = "2002-08-07T15:32:05-05:00 ";
        resDate = ParserUtils.getDate(strdate);
        expDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        expDate.set(2002, Calendar.AUGUST, 7, 20, 32, 5);
        expDate.set(Calendar.MILLISECOND, 0);
        assertEquals(expDate.getTime(), resDate);

        strdate = "2002-08-07T12:32:05+03:00 ";
        resDate = ParserUtils.getDate(strdate);
        expDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        expDate.set(2002, Calendar.AUGUST, 7, 9, 32, 5);
        expDate.set(Calendar.MILLISECOND, 0);
        assertEquals(expDate.getTime(), resDate);

        strdate = "2002-08-07T12:32:05 GMT";
        resDate = ParserUtils.getDate(strdate);
        expDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        expDate.set(2002, Calendar.AUGUST, 7, 12, 32, 5);
        expDate.set(Calendar.MILLISECOND, 0);
        assertEquals(expDate.getTime(), resDate);

        strdate = "2002-08-07T12:32:05 RET";
        resDate = ParserUtils.getDate(strdate);
        expDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        expDate.set(2002, Calendar.AUGUST, 7, 8, 32, 5); // Reunion Time = GMT+4
        expDate.set(Calendar.MILLISECOND, 0);
        assertEquals(expDate.getTime(), resDate);

        strdate = "2002-08-07T12:32:05Z";
        resDate = ParserUtils.getDate(strdate);
        expDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        expDate.set(2002, Calendar.AUGUST, 7, 12, 32, 5);
        expDate.set(Calendar.MILLISECOND, 0);
        assertEquals(expDate.getTime(), resDate);

        strdate = "2002-08-07T12:32:05+3:00 ";
        resDate = ParserUtils.getDate(strdate);
        expDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        expDate.set(2002, Calendar.AUGUST, 7, 9, 32, 5);
        expDate.set(Calendar.MILLISECOND, 0);
        assertEquals(expDate.getTime(), resDate);

        strdate = "2002-08-07T06:32:05-3:00 ";
        resDate = ParserUtils.getDate(strdate);
        expDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        expDate.set(2002, Calendar.AUGUST, 7, 9, 32, 5);
        expDate.set(Calendar.MILLISECOND, 0);
        assertEquals(expDate.getTime(), resDate);

        strdate = "2002-08-07T12:32:05GMT-02:00";
        resDate = ParserUtils.getDate(strdate);
        expDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        expDate.set(2002, Calendar.AUGUST, 7, 14, 32, 5);
        expDate.set(Calendar.MILLISECOND, 0);
        assertEquals(expDate.getTime(), resDate);
    }

    public void testUnescape() {
        String testString = "&amp;gt; &gt; &quot; &lt; &apos;";
        //System.err.println(" decoded version = "+ParserUtils.unEscape(testString));
        assertEquals("&gt; > \" < '", ParserUtils.unEscape(testString));
    }

    /**
     * Tests how the case of child-tags is converted.
     *
     * @throws Exception in case of any error.
     */
    public void testMatchCaseOfChildren() throws Exception {
        String test1 = "<a>" +
                "<Bb><C></C></Bb>" +
                "<D></D>" +
                "<E></E>" +
                "<BB></BB>" +
                "<bb></bb>" +
                "</a>";

        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new StringReader(test1));
        Element root = doc.getRootElement();
        List elements;

        elements = root.getChildren();
        assertEquals(5, elements.size());
        assertEquals("Bb", ((Element) elements.get(0)).getName());
        assertEquals("D", ((Element) elements.get(1)).getName());
        assertEquals("E", ((Element) elements.get(2)).getName());
        assertEquals("BB", ((Element) elements.get(3)).getName());
        assertEquals("bb", ((Element) elements.get(4)).getName());

        ParserUtils.matchCaseOfChildren(root, new String[]{"bB", "e"});

        elements = root.getChildren();
        assertEquals(5, elements.size());
        assertEquals("bB", ((Element) elements.get(0)).getName());
        assertEquals("D", ((Element) elements.get(1)).getName());
        assertEquals("e", ((Element) elements.get(2)).getName());
        assertEquals("bB", ((Element) elements.get(3)).getName());
        assertEquals("bB", ((Element) elements.get(4)).getName());
    }
}
