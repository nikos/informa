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

import de.nava.informa.core.FeedIF;
import de.nava.informa.core.ParseException;
import de.nava.informa.utils.InformaTestCase;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;

public class TestOPML_1_1_Parser extends InformaTestCase {

    static Collection<FeedIF> feeds;
    static URL inpURL;

    public TestOPML_1_1_Parser(String name) throws IOException, ParseException {
        super("TestOPML_1_1_Parser", name);
        if (feeds == null) {
            File inpFile = new File(getDataDir(), "favchannels.opml");
            feeds = OPMLParser.parse(inpFile);
            // for later reference
            inpURL = inpFile.toURI().toURL();
        }
    }

    public void testNumberFeedsReadIn() {
        assertEquals(12, feeds.size());
    }

    public void testReadInFeeds() {
        Iterator<FeedIF> it = feeds.iterator();
        boolean found = false;
        while (it.hasNext()) {
            FeedIF feed = it.next();
            if (feed.getTitle().startsWith("Google Weblog")) {
                assertEquals("Google Weblog", feed.getTitle());
                assertEquals("rss", feed.getContentType());
                assertEquals("http://google.blogspace.com/rss", feed.getLocation().toString());
                assertEquals("http://google.blogspace.com/", feed.getSite().toString());
                found = true;
                break;
            }
        }
        assertTrue("Couldn't find item looked for.", found);
    }

    public void testReadInOPML_10_PodcastAlleyFeed() throws IOException, ParseException {
        Collection<FeedIF> c = OPMLParser.parse(new File(getDataDir(), "opml-1.0-podcastalley.com.xml"));
        assertEquals(10, c.size());
    }
}
