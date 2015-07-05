//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.exporters;

import de.nava.informa.core.ChannelExporterIF;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.core.ParseException;
import de.nava.informa.impl.basic.Channel;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.impl.basic.Item;
import de.nava.informa.parsers.FeedParser;
import de.nava.informa.utils.InformaTestCase;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class TestRSS_1_0_Exporter extends InformaTestCase {

    public TestRSS_1_0_Exporter(String name) {
        super("TestRSS_1_0_Exporter", name);
    }

    public void testExportChannel()
            throws IOException, ParseException {

        // TODO: refactor this into more reusable test case !!!

        String ch_title = "The Great Demo Channel";
        String ch_desc = "Just a very simple short description.";

        // create dummy channel
        ChannelIF channel = new Channel(ch_title);
        channel.setDescription(ch_desc);
        channel.setSite(new URL("http://nava.de"));
        channel.setLocation(new URL("http://nava.de/news.rdf"));
        ItemIF itemA = new Item("Bugo", "All about it!",
                new URL("http://nava.de/huhu2002"));
        itemA.setFound(new Date());
        channel.addItem(itemA);
        // TODO: what about markup here ???
        ItemIF itemB = new Item("SoCool",
                "????**$12 @??? # <strong>should</strong> work",
                new URL("http://nava.de/very/nested/98"));
        itemB.setFound(new Date());
        channel.addItem(itemB);
        assertEquals(2, channel.getItems().size());
        // export this channel to file (encoding: utf-8)
        String basename = "export-rss10.xml";
        String exp_file = getOutputDir() + FS + basename;
        ChannelExporterIF exporter = new RSS_1_0_Exporter(exp_file);
        exporter.write(channel);

        // read in again
        File inpFile = new File(exp_file);
        channel = FeedParser.parse(new ChannelBuilder(), inpFile);

        assertEquals(ch_title, channel.getTitle());
        assertEquals(ch_desc, channel.getDescription());

        Collection items = channel.getItems();
        assertEquals(2, items.size());
        Iterator it = items.iterator();
        ItemIF item = (ItemIF) it.next();
        if (item.equals(itemA)) {
            assertEquals(item, itemA);
            item = (ItemIF) it.next();
            assertEquals(item, itemB);
        } else {
            assertEquals(item, itemB);
            item = (ItemIF) it.next();
            assertEquals(item, itemA);
        }
    }

}
