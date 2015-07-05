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

import de.nava.informa.utils.toolkit.ChannelRecord;

import java.util.Comparator;

/**
 * Comparator for <code>ChannelRecord</code> class. Uses priority setting to decide which
 * record goes first.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public class PriorityComparator implements Comparator {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws ClassCastException if the arguments' types prevent them from
     *                            being compared by this Comparator.
     */
    public final int compare(Object o1, Object o2) {
        final ChannelRecord r1 = (ChannelRecord) o1;
        final ChannelRecord r2 = (ChannelRecord) o2;

        final int p1 = r1.getPriority();
        final int p2 = r2.getPriority();

        int result = 0;

        if (p1 < p2) {
            result = -1;
        } else if (p2 > p1) {
            result = 1;
        }

        return result;
    }
}
