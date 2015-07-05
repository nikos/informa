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
import de.nava.informa.core.ParseException;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.utils.InformaTestCase;

import java.io.File;
import java.io.IOException;

public class TestRSS_0_92_Parser extends InformaTestCase {

    public TestRSS_0_92_Parser(String name)
            throws IOException, ParseException {

        super("TestRSS_0_92_Parser", name);
        this.method_name = name;
    }

    public void testParseW3CSynd() throws Exception {
        File inpFile = new File(getDataDir(), "juancolecom.rss");
        ChannelIF channel_juan = FeedParser.parse(new ChannelBuilder(), inpFile);
        assertEquals("Juan Cole   *  Informed Comment  *", channel_juan.getTitle());
        assertEquals(25, channel_juan.getItems().size());
        // TODO: For the time being RSS 0.92 is handled by the RSS 0.91 parser
        assertEquals(ChannelFormat.RSS_0_91, channel_juan.getFormat());
    }

}
