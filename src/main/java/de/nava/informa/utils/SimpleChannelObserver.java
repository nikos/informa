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
import de.nava.informa.core.ChannelObserverIF;
import de.nava.informa.core.ItemIF;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Simple implementation of the ChannelObserverIF interface for
 * keeping track of the added news item (and also to properly handle
 * it by the logging facility).
 *
 * @author Niko Schmuck
 */
public class SimpleChannelObserver implements ChannelObserverIF {

    private static Log logger = LogFactory.getLog(SimpleChannelObserver.class);

    private ItemIF myAddedItem;

    public SimpleChannelObserver() {
        super();
    }

    public ItemIF getMyAddedItem() {
        return myAddedItem;
    }

    // ------------------------------------------------------------
    // Implementation of ChannelObserverIF
    // ------------------------------------------------------------

    public void itemAdded(ItemIF newItem) {
        myAddedItem = newItem;
        logger.info("A new item was added: " + newItem);
    }

    public void channelRetrieved(ChannelIF channel) {
        logger.info("Channel " + channel + " updated.");
    }

}
