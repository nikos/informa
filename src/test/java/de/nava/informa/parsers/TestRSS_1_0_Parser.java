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

import de.nava.informa.core.ChannelFormat;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.core.ParseException;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.utils.InformaTestCase;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TestRSS_1_0_Parser extends InformaTestCase {

    static ChannelIF channel;
    static URL inpURL;

    public TestRSS_1_0_Parser(String name)
            throws IOException, ParseException {

        super("TestRSS_1_0_Parser", name);
        this.method_name = name;
        if (channel == null) {
            File inpFile = new File(getDataDir(), "bloggy.rdf");
            channel = FeedParser.parse(new ChannelBuilder(), inpFile);
            // for later reference
            inpURL = inpFile.toURI().toURL();
        }
    }

    public void testCreatedChannel() {
        assertEquals("bloggy", channel.getTitle());
        assertEquals("Thoughts, rants, and photos", channel.getDescription());
        assertEquals(inpURL, channel.getLocation());
        assertEquals("http://bloggy.com/mt/", channel.getSite().toString());

        assertEquals(channel.getDescription(), channel.getElementValue("description"));

        String attribute = channel.getAttributeValue("admin:generatorAgent", "rdf:resource");
        assertEquals("http://www.movabletype.org/?v=2.51", attribute);
    }

    public void testCreatedItems() {
        assertEquals(15, channel.getItems().size());
        ItemIF item = searchForItem(channel, "We know what culture is");
        assertNotNull("Item not found", item);
        assertEquals("We know what culture is, right?", item.getTitle());
        assertEquals("http://bloggy.com/mt/archives/000842.html",
                item.getLink().toString());
        assertEquals(100, item.getDescription().length());
        assertEquals("War", item.getSubject());
        assertEquals("barry", item.getCreator());
        Calendar expDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        expDate.set(2003, Calendar.APRIL, 1, 18, 43, 21);
        expDate.set(Calendar.MILLISECOND, 0);
        assertEquals(expDate.getTime(), item.getDate());
    }

    public void testParseGoogleWeblog() throws Exception {
        File inpFile = new File(getDataDir(), "google-weblog.rdf");
        ChannelIF channel_gw = FeedParser.parse(new ChannelBuilder(), inpFile);
        assertEquals("Google Weblog", channel_gw.getTitle());
        assertEquals(15, channel_gw.getItems().size());
        assertEquals(ChannelFormat.RSS_1_0, channel_gw.getFormat());
    }

    public void testParseW3CSynd() throws Exception {
        File inpFile = new File(getDataDir(), "w3c-synd.rdf");
        ChannelIF channel_w3c = FeedParser.parse(new ChannelBuilder(), inpFile);
        assertEquals("World Wide Web Consortium", channel_w3c.getTitle());
        assertEquals(7, channel_w3c.getItems().size());
        assertEquals(ChannelFormat.RSS_1_0, channel_w3c.getFormat());
    }

    public void testParseContentEncoded() throws Exception {
        File inpFile = new File(getDataDir(), "rss10-content-encoded.rdf");
        ChannelIF channel = FeedParser.parse(new ChannelBuilder(), inpFile);

        ItemIF[] items = channel.getItems().toArray(new ItemIF[channel.getItems().size()]);
        assertEquals(3, items.length);
        assertEquals("1&amp;", items[0].getDescription());
        assertEquals("2", items[1].getDescription());
        assertEquals("3&", items[2].getDescription());
    }
}
