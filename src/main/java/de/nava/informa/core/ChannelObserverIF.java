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

/**
 * A class implementing this interface most likely wants to react on
 * the observed event.
 *
 * @author Niko Schmuck
 */
public interface ChannelObserverIF {

    /**
     * Called when a new Item is added to this Channel
     *
     * @param newItem - Item that was added.
     */
    void itemAdded(ItemIF newItem);

    /**
     * Called when a new Channel is added
     *
     * @param channel - Channel that was added
     */
    void channelRetrieved(ChannelIF channel);

}
