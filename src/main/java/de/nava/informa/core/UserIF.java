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
 * This interface is implemented by objects storing user specific information.
 *
 * @author Niko Schmuck
 */
public interface UserIF extends WithIdMIF, WithNameMIF {

    /**
     * @return A collection of ChannelSubscriptionIF objects.
     */
    Collection getChannelSubscriptions();

    void addChannelSubscription(ChannelSubscriptionIF subscription);

    void removeChannelSubscription(ChannelSubscriptionIF subscription);

    /**
     * @return A collection of ItemMetadataIF objects.
     */
    Collection getItemMetadata();

    void addItemMetadata(ItemMetadataIF metadata);

    void removeItemMetadata(ItemMetadataIF metadata);

}
