//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils.cleaner;

import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.utils.manager.PersistenceManagerException;
import de.nava.informa.utils.manager.PersistenceManagerIF;

/**
 * Watches for events about unwanted items and removes them from channel using given manager.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public class PersistenceObserver implements CleanerObserverIF {
    private PersistenceManagerIF manager;

    /**
     * Creates observer.
     *
     * @param manager manager to use for persistent changes.
     * @throws IllegalArgumentException if manager isn't specified.
     */
    public PersistenceObserver(PersistenceManagerIF manager) {
        if (manager == null) {
            throw new IllegalArgumentException("Manager should be specified.");
        }

        this.manager = manager;
    }

    /**
     * Invoked when cleanup engine finds unwanted item.
     *
     * @param item    unwanted item.
     * @param channel channel this item resides in.
     */
    public final void unwantedItem(ItemIF item, ChannelIF channel) {
        try {
            manager.deleteItem(item);
        } catch (PersistenceManagerException e) {
            // We can do nothing here.
        }
    }

    /**
     * Invoked by cleanup engine when cleaning of the channel has started.
     *
     * @param channel channel being cleaned.
     */
    public void cleaningStarted(ChannelIF channel) {
    }

    /**
     * Invoked by cleanup engine when cleaning of the channel has finished.
     *
     * @param channel channel being cleaned.
     */
    public void cleaningFinished(ChannelIF channel) {
    }
}
