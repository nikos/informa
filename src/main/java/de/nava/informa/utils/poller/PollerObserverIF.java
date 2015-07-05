//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils.poller;

import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;

/**
 * Observer of events outgoing from Poller.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public interface PollerObserverIF {
    /**
     * Invoked by Poller when new item is approved for addition. Item is transient
     * and should be added to specified channel.
     *
     * @param item    item added.
     * @param channel destination channel.
     */
    void itemFound(ItemIF item, ChannelIF channel);

    /**
     * Invoked by Poller when poller of the channel failed.
     *
     * @param channel channel.
     * @param e       original cause of failure.
     */
    void channelErrored(ChannelIF channel, Exception e);

    /**
     * Invoked when Poller detected changes in channel information (title and etc).
     *
     * @param channel channel.
     */
    void channelChanged(ChannelIF channel);

    /**
     * Invoked by Poller when checking of the channel started.
     *
     * @param channel channel.
     */
    void pollStarted(ChannelIF channel);

    /**
     * Invoked by Poller when checking of the channel finished successfully.
     *
     * @param channel channel.
     */
    void pollFinished(ChannelIF channel);
}
