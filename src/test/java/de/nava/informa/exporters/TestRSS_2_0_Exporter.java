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

import de.nava.informa.core.CategoryIF;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.core.ParseException;
import de.nava.informa.impl.basic.Channel;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.impl.basic.Item;
import de.nava.informa.parsers.FeedParser;
import de.nava.informa.utils.InformaTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TestRSS_2_0_Exporter extends InformaTestCase {

    public TestRSS_2_0_Exporter(String name) {
        super("TestRSS_2_0_Exporter", name);
    }

    public void testExportChannel()
            throws IOException, MalformedURLException, ParseException {

        String ch_title = "The Great Demo Channel";
        String ch_desc = "Just a very simple short description.";

        ChannelBuilder builder = new ChannelBuilder();

        // create dummy channel
        ChannelIF channel = new Channel(ch_title);
        channel.setDescription(ch_desc);
        channel.setSite(new URL("http://nava.de"));
        ItemIF itemA = new Item("Bugo", "All about it!",
                new URL("http://nava.de/huhu2002"));
        itemA.setFound(new Date());
        CategoryIF cat1 = builder.createCategory(null, "cat1");
        CategoryIF cat1a = builder.createCategory(cat1, "cat1a");
        builder.createCategory(cat1a, "cat1aa");
        CategoryIF cat1ab = builder.createCategory(cat1a, "cat1ab");
        builder.createCategory(cat1ab, "cat1aba");
        builder.createCategory(cat1ab, "cat1abb");
        builder.createCategory(cat1a, "cat1ac");
        builder.createCategory(cat1, "cat1b");
        CategoryIF cat1c = builder.createCategory(cat1, "cat1c");
        builder.createCategory(cat1c, "cat1ca");
        CategoryIF cat2 = builder.createCategory(null, "cat2");
        itemA.addCategory(cat1);
        itemA.addCategory(cat2);
        assertEquals(2, itemA.getCategories().size());
        channel.addItem(itemA);
        // TODO: what about markup here ???
        ItemIF itemB = new Item("SoCool",
                "????**$12 @??? # <strong>should</strong> work",
                new URL("http://nava.de/very/nested/98"));
        itemB.setFound(new Date());
        CategoryIF catX = builder.createCategory(null, "catX");
        itemB.addCategory(catX);
        assertEquals(1, itemB.getCategories().size());
        channel.addItem(itemB);
        assertEquals(2, channel.getItems().size());
        // export this channel to file (encoding: utf-8)
        String basename = "export-rss20.xml";
        String exp_file = getOutputDir() + FS + basename;
        RSS_2_0_Exporter exporter = new RSS_2_0_Exporter(exp_file);
        exporter.write(channel);

        // clean channel object
        channel = null;

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

            // test that item categories match
            List catList = (List) item.getCategories();
            assertEquals(catList.size(), 2);
            Iterator itCat = catList.iterator();
            CategoryIF cat = (CategoryIF) itCat.next();
            if (cat.equals(cat1)) {
                assertEquals(cat.getTitle(), cat1.getTitle());
            } else {
                assertEquals(cat.getTitle(), cat2.getTitle());
            }

            item = (ItemIF) it.next();
            assertEquals(item, itemB);
        } else {
            assertEquals(item, itemB);

            // test that item categories match
            List catList = (List) item.getCategories();
            assertEquals(catList.size(), 1);
            Iterator itCat = catList.iterator();
            CategoryIF cat = (CategoryIF) itCat.next();
            assertEquals(cat.getTitle(), catX.getTitle());

            item = (ItemIF) it.next();
            assertEquals(item, itemA);
        }
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new TestSuite(TestRSS_2_0_Exporter.class));
        return suite;
    }

    public static void main(String[] args) {
        TestRunner.run(suite());
    }

}
