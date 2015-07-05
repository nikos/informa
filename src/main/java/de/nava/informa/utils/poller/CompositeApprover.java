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
 * Composite approver uses all of its sub-approvers to form decision.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
class CompositeApprover implements PollerApproverIF {
    private List<PollerApproverIF> approvers;

    CompositeApprover() {
        approvers = new Vector<>();
    }

    /**
     * Decides whether it's possible to add item to the channel or no.
     *
     * @param item    item to add.
     * @param channel destination channel.
     * @return TRUE if addition is allowed.
     */
    public final boolean canAddItem(ItemIF item, ChannelIF channel) {
        boolean result = true;
        int i = 0;
        final int size = approvers.size();
        while (i < size && result) {
            final PollerApproverIF approver = approvers.get(i);
            result = approver.canAddItem(item, channel);
            i++;
        }

        return result;
    }

    /**
     * Adds new approver to the list.
     *
     * @param approver new approver.
     */
    public final void add(PollerApproverIF approver) {
        if (!approvers.contains(approver)) {
            approvers.add(approver);
        }
    }

    /**
     * Removes approver from the list.
     *
     * @param approver registered approver.
     */
    public final void remove(PollerApproverIF approver) {
        approvers.remove(approver);
    }
}
