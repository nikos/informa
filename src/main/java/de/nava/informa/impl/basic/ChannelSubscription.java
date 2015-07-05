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

import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ChannelSubscriptionIF;

/**
 * In-Memory implementation of the ChannelSubscriptionIF interface.
 *
 * @author Niko Schmuck (niko@nava.de)
 */
public class ChannelSubscription implements ChannelSubscriptionIF {

    private static final long serialVersionUID = -5353083835760132316L;

    private ChannelIF channel;
    private boolean active;
    private int updateInterval;

    /**
     * Default constructor sets to an inactive channel (with an update
     * interval of 3 hours, used when activated).
     *
     * @param channel the channel.
     */
    public ChannelSubscription(ChannelIF channel) {
        this(channel, false, 3 * 60 * 60);
    }

    public ChannelSubscription(ChannelIF channel, boolean active, int interval) {
        this.channel = channel;
        this.active = active;
        this.updateInterval = interval;
    }

    // --------------------------------------------------------------
    // implementation of ChannelSubscriptionIF interface
    // --------------------------------------------------------------

    public ChannelIF getChannel() {
        return channel;
    }

    public void setChannel(ChannelIF channel) {
        this.channel = channel;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(int interval) {
        this.updateInterval = interval;
    }

}
