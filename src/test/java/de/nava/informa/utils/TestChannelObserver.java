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

import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ChannelObservableIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.impl.basic.Channel;
import de.nava.informa.impl.basic.Item;

public class TestChannelObserver extends InformaTestCase {

    public TestChannelObserver(String name) {
        super("TestChannelObserver", name);
    }

    public void testObserve() {
        ChannelIF channel = new Channel("Niko's log");
        SimpleChannelObserver observer = new SimpleChannelObserver();
        ((ChannelObservableIF) channel).addObserver(observer);
        assertEquals(0, channel.getItems().size());
        ItemIF item = new Item("Bongo", "Rongoo", null);
        channel.addItem(item);
        assertEquals(1, channel.getItems().size());
        assertTrue(channel.getItems().contains(item));
        assertEquals(item, observer.getMyAddedItem());

    }

}
