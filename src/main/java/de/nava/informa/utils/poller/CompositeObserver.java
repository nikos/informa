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

import java.util.List;
import java.util.Vector;

/**
 * Composite observer delivers all received events to sub observers.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
class CompositeObserver implements PollerObserverIF {
    private List<PollerObserverIF> observers = new Vector<>();

    /**
     * Invoked by Poller when new item is approved for addition. Item is transient
     * and should be added to specified channel.
     *
     * @param item    item added.
     * @param channel destination channel.
     */
    public final void itemFound(ItemIF item, ChannelIF channel) {
        for (final PollerObserverIF observer : observers) {
            try {
                observer.itemFound(item, channel);
            } catch (RuntimeException e) {
                // Do not care about exceptions from sub-observers.
            }
        }
    }

    /**
     * Invoked by Poller when poller of the channel failed.
     *
     * @param channel channel.
     * @param e       original cause of failure.
     */
    public final void channelErrored(ChannelIF channel, Exception e) {
        for (final PollerObserverIF observer : observers) {
            try {
                observer.channelErrored(channel, e);
            } catch (RuntimeException e1) {
                // Do not care about exceptions from sub-observers.
            }
        }
    }

    /**
     * Invoked when Poller detected changes in channel information (title and etc).
     *
     * @param channel channel.
     */
    public final void channelChanged(ChannelIF channel) {
        for (final PollerObserverIF observer : observers) {
            try {
                observer.channelChanged(channel);
            } catch (RuntimeException e1) {
                // Do not care about exceptions from sub-observers.
            }
        }
    }

    /**
     * Invoked by Poller when checking of the channel started.
     *
     * @param channel channel.
     */
    public final void pollStarted(ChannelIF channel) {
        for (final PollerObserverIF observer : observers) {
            try {
                observer.pollStarted(channel);
            } catch (RuntimeException e1) {
                // Do not care about exceptions from sub-observers.
            }
        }
    }

    /**
     * Invoked by Poller when checking of the channel finished.
     *
     * @param channel channel.
     */
    public final void pollFinished(ChannelIF channel) {
        for (final PollerObserverIF observer : observers) {
            try {
                observer.pollFinished(channel);
            } catch (RuntimeException e1) {
                // Do not care about exceptions from sub-observers.
            }
        }
    }

    /**
     * Adds new observer to the list.
     *
     * @param observer new observer.
     */
    public final void add(PollerObserverIF observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Removes observer from the list.
     *
     * @param observer registered observer.
     */
    public final void remove(PollerObserverIF observer) {
        observers.remove(observer);
    }
}
