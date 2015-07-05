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
import java.util.Date;
import java.util.Iterator;

public class TestRSS_0_91_Parser extends InformaTestCase {

    static ChannelIF channel;
    static URL inpURL;

    public TestRSS_0_91_Parser(String name)
            throws IOException, ParseException {

        super("TestRSS_0_91_Parser", name);
        if (channel == null) {
            File inpFile = new File(getDataDir(), "xmlhack-0.91.xml");
            channel = FeedParser.parse(new ChannelBuilder(), inpFile);
            // for later reference
            inpURL = inpFile.toURL();
        }
    }

    public void testCreatedChannel() {
        assertEquals("xmlhack", channel.getTitle());
        assertEquals("Developer news from the XML community",
                channel.getDescription());
        assertEquals(inpURL, channel.getLocation());
        assertEquals("http://www.xmlhack.com", channel.getSite().toString());
        assertEquals(ChannelFormat.RSS_0_91, channel.getFormat());

        String[] elements = {"title", "url", "link", "width", "height", "description"};
        String[] values = channel.getElementValues("image", elements);
        assertEquals("xmlhack", values[0]);
        assertEquals("http://www.xmlhack.com/images/mynetscape88.gif", values[1]);
        assertEquals("http://www.xmlhack.com", values[2]);
        assertEquals("88", values[3]);
        assertEquals("31", values[4]);
        assertEquals("News, opinions, tips and issues concerning XML development", values[5]);
    }

    public void testCreatedItems() {
        assertEquals(6, channel.getItems().size());
        Iterator it = channel.getItems().iterator();
        assertNotNull("Could not get items iterator", it);
        ItemIF item = searchForItem(channel, "Revised");
        assertNotNull("Item not found", item);
        assertEquals("Revised DOM Level 3 drafts", item.getTitle());
        assertEquals("http://www.xmlhack.com/read.php?item=1612",
                item.getLink().toString());
        assertEquals(196, item.getDescription().length());
        assertNotNull(item.getFound());
        Date now = new Date();
        assertTrue(now.after(item.getFound()));
    }

}
