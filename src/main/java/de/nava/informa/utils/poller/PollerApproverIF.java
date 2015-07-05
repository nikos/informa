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
 * Approver of item addition as result of poll.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public interface PollerApproverIF {

    /**
     * Decides whether it's possible to add item to the channel or no.
     *
     * @param item    item to add.
     * @param channel destination channel.
     * @return TRUE if addition is allowed.
     */
    boolean canAddItem(ItemIF item, ChannelIF channel);
}
