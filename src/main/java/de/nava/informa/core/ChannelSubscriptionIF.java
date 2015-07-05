//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.core;

import java.io.Serializable;

/**
 * Implementing classes hold information about wether a channel should
 * be updated or not, when the channel was last time updated
 * (retrieved) and in which interval subsequent updated should be
 * executed.
 *
 * @author Niko Schmuck (niko@nava.de)
 */
public interface ChannelSubscriptionIF extends Serializable, WithChannelMIF {

    boolean isActive();

    /**
     * Activate/Deactivate channel update behaviour.
     *
     * @param active - whether this channel should be updated at all or not.
     */
    void setActive(boolean active);


    int getUpdateInterval();

    /**
     * Sets the interval time between channel updates.
     *
     * @param interval - time in seconds between channel update retrievals.
     */
    void setUpdateInterval(int interval);

}
