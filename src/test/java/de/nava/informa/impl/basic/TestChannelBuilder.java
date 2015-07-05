//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.impl.basic;

import de.nava.informa.core.ChannelBuilderIF;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.utils.InformaTestCase;

import java.net.MalformedURLException;
import java.net.URL;

public class TestChannelBuilder extends InformaTestCase {

    public TestChannelBuilder(String testname) {
        super("TestChannelBuilder", testname);
    }

    public void testCreateChannel() {
        ChannelBuilderIF builder = new ChannelBuilder();
        ChannelIF chA = builder.createChannel("myChannel");
        assertEquals("myChannel", chA.getTitle());
    }

    public void testCreateItem() throws MalformedURLException {
        ChannelBuilderIF builder = new ChannelBuilder();
        ChannelIF chA = builder.createChannel("myChannel");
        ItemIF itA = builder.createItem(chA, "first item", "descr of item",
                new URL("http://sf.net/projects/informa"));
        itA.setCreator("TestChannelBuilder");
        assertEquals("first item", itA.getTitle());
        itA = null;
        // test retrieval
        ItemIF itB = (ItemIF) chA.getItems().iterator().next();
        assertEquals("first item", itB.getTitle());
        assertEquals("TestChannelBuilder", itB.getCreator());
    }

}
