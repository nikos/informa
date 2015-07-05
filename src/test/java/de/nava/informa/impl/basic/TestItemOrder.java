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
import java.util.Iterator;

public class TestItemOrder extends InformaTestCase {

    private static ChannelBuilderIF builder = new ChannelBuilder();

    public TestItemOrder(String testname) {
        super("TestItemOrder", testname);
    }

    private static ChannelIF prepare() throws MalformedURLException {
        ChannelIF chA = builder.createChannel("myChannel");
        builder.createItem(chA, "first item", "descr1", new URL("http://1.net/"));
        builder.createItem(chA, "second item", "22", new URL("http://2.net/"));
        builder.createItem(chA, "third item", "333", new URL("http://3.net/"));
        builder.createItem(chA, "fourth item", "4", new URL("http://4.net/"));
        builder.createItem(chA, "fifth item", "5555", new URL("http://5.net/"));
        builder.createItem(chA, "sixth item", "6", new URL("http://6.net/"));
        builder.createItem(chA, "seventh item", "7777", new URL("http://7.net/"));
        builder.createItem(chA, "eight item", "8", new URL("http://8.net/"));
        return chA;
    }

    public void testCreatedItemCount() throws MalformedURLException {
        ChannelIF channel = prepare();
        assertEquals(8, channel.getItems().size());
    }

    public void testCreatedItemInOrder() throws MalformedURLException {
        ChannelIF channel = prepare();
        Iterator it = channel.getItems().iterator();
        int idx = 1;
        while (it.hasNext()) {
            ItemIF item = (ItemIF) it.next();
            assertEquals("http://" + idx + ".net/", item.getLink().toString());
            idx++;
        }
    }

    public void testCreatedItemAddOne() throws MalformedURLException {
        ChannelIF channel = prepare();
        ItemIF firstItem = (ItemIF) channel.getItems().iterator().next();
        channel.removeItem(firstItem);
        assertEquals(7, channel.getItems().size());
        builder.createItem(channel, "another one", "9", new URL("http://9.net/"));
        assertEquals(8, channel.getItems().size());
        Iterator it = channel.getItems().iterator();
        int idx = 2;
        while (it.hasNext()) {
            ItemIF item = (ItemIF) it.next();
            assertEquals("http://" + idx + ".net/", item.getLink().toString());
            idx++;
        }
    }

    public void testRetrieveItem() throws MalformedURLException {
        ChannelIF channel = prepare();
        ItemIF firstItem = (ItemIF) channel.getItems().iterator().next();
        long firstId = firstItem.getId();
        ItemIF retrievedItem = channel.getItem(firstId);
        assertEquals(firstItem, retrievedItem);
    }

}
