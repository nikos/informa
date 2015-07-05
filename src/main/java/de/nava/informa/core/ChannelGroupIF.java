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

import java.util.Collection;

/**
 * Interface to allow to implement a container of channels that may be
 * used by a channel registry (through a front-end) or as the entry
 * point for an application using this library. A ChannelGroupIF
 * object may also reflect the root element of a XML file persisted
 * from the channel object model.</p>
 *
 * @author Niko Schmuck (niko@nava.de)
 */
public interface ChannelGroupIF extends WithIdMIF, WithTitleMIF, WithChildrenMIF {

    void add(ChannelIF channel);

    void remove(ChannelIF channel);

    /**
     * @return A collection of ChannelIF objects.
     */
    Collection<ChannelIF> getAll();

    ChannelIF getById(long id);

    ChannelGroupIF getParent();

    void setParent(ChannelGroupIF parent);

    void addChild(ChannelGroupIF child);

    void removeChild(ChannelGroupIF child);
}
