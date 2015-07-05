//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.parsers;

import de.nava.informa.core.ChannelBuilderIF;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ParseException;
import de.nava.informa.utils.InformaTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.net.URL;

/**
 * Test class which reads in a textfile (containing one URL per line)
 * and trying to parse each one as an own test case. Expects at least
 * one news item in each individual assertion.
 *
 * @author Niko Schmuck
 */
public class TestFeedParser extends InformaTestCase {

    private static Log logger = LogFactory.getLog(InformaTestCase.class);

    private String testURL;
    private ChannelBuilderIF builder;

    public TestFeedParser(String testMethodName, String testURL) {
        super("TestFeedParser", testMethodName);
        this.testURL = testURL;
        this.builder = new de.nava.informa.impl.basic.ChannelBuilder();
    }

    public void testParseNewsFeedValidChannel() throws Exception {
        logger.info("Reading in feed from " + testURL);
        ChannelIF channel = FeedParser.parse(builder, new URL(testURL));
        assertNotNull("Failed parsing channel " + testURL, channel.getItems());
        assertTrue("Expected at least one item at channel " + testURL,
                channel.getItems().size() > 0);
    }

    public void testNonExistingDTD() throws Exception {
        String sampleFeed = "<!DOCTYPE rss PUBLIC \"-//Netscape Communications//DTD RSS 0.91//EN\"\n" +
                " \"http://MISSING.netscape.com/publish/formats/rss-0.91.dtd\">\n" +
                "<rss version=\"0.91\">\n" +
                "<channel>\n" +
                "<title>Test</title>\n" +
                "</channel>\n" +
                "</rss>";

        try {
            FeedParser.parse(builder, new StringReader(sampleFeed));
        } catch (ParseException e) {
            e.printStackTrace();
            fail("Failed to parse feed with non-existing DND.");
        }
    }

    public static Test suite() throws Exception {
        TestSuite suite = new TestSuite();
        // Read in file and construct test suite
        String line;
        BufferedReader rdr = new BufferedReader(new FileReader(getDataDir() + FS + "newsfeeds.txt"));
        while ((line = rdr.readLine()) != null) {
            if (line.startsWith("#")) { // Ignore line
                continue;
            }
            suite.addTest(new TestFeedParser("testParseNewsFeedValidChannel", line));
        }

        suite.addTest(new TestFeedParser("testNonExistingDTD", null));

        return suite;
    }
}
