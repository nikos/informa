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
 * Classes implementing this interface may want to inform the
 * subscribed observers that a specific channel event has
 * happened (like for example a new item was found).
 *
 * <p>This interface is usually called Subject in the Observer
 * pattern.</p>
 *
 * @author Niko Schmuck
 */
public interface ChannelObservableIF {

    /**
     * Adds an observer to the set of observers for this object.
     */
    void addObserver(ChannelObserverIF o);

    /**
     * Removes an observer from the set of observers of this object.
     */
    void removeObserver(ChannelObserverIF o);

}
