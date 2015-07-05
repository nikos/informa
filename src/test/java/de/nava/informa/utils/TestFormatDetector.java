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

import de.nava.informa.core.ChannelFormat;
import de.nava.informa.core.UnsupportedFormatException;

import java.io.File;

public class TestFormatDetector extends InformaTestCase {

    public TestFormatDetector(String name) {
        super("TestFormatDetector", name);
    }

    public void testFormatRSS091xmlhack() throws Exception {
        File inpFile = new File(getDataDir(), "xmlhack-0.91.xml");
        ChannelFormat actualFormat = FormatDetector.getFormat(inpFile.toURI().toURL());
        assertEquals(ChannelFormat.RSS_0_91, actualFormat);
    }

    public void testFormatRSS091aeden() throws Exception {
        File inpFile = new File(getDataDir(), "aeden.rss");
        ChannelFormat actualFormat = FormatDetector.getFormat(inpFile.toURI().toURL());
        assertEquals(ChannelFormat.RSS_0_91, actualFormat);
    }

    public void testFormatRSS092gratefuldead() throws Exception {
        File inpFile = new File(getDataDir(), "gratefulDead-rss-0.92.xml");
        ChannelFormat actualFormat = FormatDetector.getFormat(inpFile.toURI().toURL());
        assertEquals(ChannelFormat.RSS_0_92, actualFormat);
    }

    public void testFormatRSS10xmlhack() throws Exception {
        File inpFile = new File(getDataDir(), "xmlhack-1.0.xml");
        ChannelFormat actualFormat = FormatDetector.getFormat(inpFile.toURI().toURL());
        assertEquals(ChannelFormat.RSS_1_0, actualFormat);
    }

    public void testFormatRSS10slashdot() throws Exception {
        File inpFile = new File(getDataDir(), "slashdot.rdf");
        ChannelFormat actualFormat = FormatDetector.getFormat(inpFile.toURI().toURL());
        assertEquals(ChannelFormat.RSS_1_0, actualFormat);
    }

    public void testFormatRSS10googleweblog() throws Exception {
        File inpFile = new File(getDataDir(), "google-weblog.rdf");
        ChannelFormat actualFormat = FormatDetector.getFormat(inpFile.toURI().toURL());
        assertEquals(ChannelFormat.RSS_1_0, actualFormat);
    }

    public void testFormatRSS10w3csynd() throws Exception {
        File inpFile = new File(getDataDir(), "w3c-synd.rdf");
        ChannelFormat actualFormat = FormatDetector.getFormat(inpFile.toURI().toURL());
        assertEquals(ChannelFormat.RSS_1_0, actualFormat);
    }

    public void testFormatRSS20A() throws Exception {
        File inpFile = new File(getDataDir(), "informa-projnews.xml");
        ChannelFormat actualFormat = FormatDetector.getFormat(inpFile.toURI().toURL());
        assertEquals(ChannelFormat.RSS_2_0, actualFormat);
    }

    public void testFormatRSS20B() throws Exception {
        File inpFile = new File(getDataDir(), "snipsnap-org.rss");
        ChannelFormat actualFormat = FormatDetector.getFormat(inpFile.toURI().toURL());
        assertEquals(ChannelFormat.RSS_2_0, actualFormat);
    }

    public void testUnsupportedFormat() throws Exception {
        File inpFile = new File(getDataDir(), "dir-xml-rpc-com.opml");
        try {
            FormatDetector.getFormat(inpFile.toURI().toURL());
            fail("This format is not yet supported ...");
        } catch (UnsupportedFormatException e) {
            assertTrue(true);
        }
    }

    public void testAtom0_3() throws Exception {
        File inpFile = new File(getDataDir(), "atom0-3.xml");
        ChannelFormat actualFormat = FormatDetector.getFormat(inpFile.toURI().toURL());
        assertEquals(ChannelFormat.ATOM_0_3, actualFormat);
    }
}
