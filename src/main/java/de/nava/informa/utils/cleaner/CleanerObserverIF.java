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

/**
 * Observers receive notification events from the cleaning engine when engine
 * finds items matching all of the Matchers' rules.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public interface CleanerObserverIF {

    /**
     * Invoked when cleanup engine finds unwanted item.
     *
     * @param item    unwanted item.
     * @param channel channel this item resides in.
     */
    void unwantedItem(ItemIF item, ChannelIF channel);

    /**
     * Invoked by cleanup engine when cleaning of the channel has started.
     *
     * @param channel channel being cleaned.
     */
    void cleaningStarted(ChannelIF channel);

    /**
     * Invoked by cleanup engine when cleaning of the channel has finished.
     *
     * @param channel channel being cleaned.
     */
    void cleaningFinished(ChannelIF channel);
}
