//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils;

import de.nava.informa.core.ChannelIF;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Custom comparator for ChannelIF objects.  Not a traditional
 * comparator as there are no order criteria for ChannelIF
 * objects.  However, there are three possible states:
 * CHANNEL_MISMATCH: Two ChannelIF's objects are not equal
 * according to their equals() method
 * CHANNEL_IDENTICAL: Two ChannelIF's are equal according
 * to their equals() method and contain the same ItemIF's
 * CHANNEL_CHANGED: Two ChannelIF's are equal according to
 * their equals() method, but contain different ItemIF's
 *
 * @author Jonathan Krebs (Jonathan.Krebs@ngc.com)
 */
public final class ChannelComparator implements Comparator {

    // ----- Valid channel comparison values

    /**
     * Channels are not the same
     */
    public static final int CHANNEL_MISMATCH = -1;

    /**
     * Channels are the same but contain different items
     */
    public static final int CHANNEL_IDENTICAL = 0;

    /**
     * Channels are the same and contain the same items
     */
    public static final int CHANNEL_CHANGED = 1;

    public ChannelComparator() {
    }

    public int compare(Object obj1, Object obj2) {

        if (obj1 instanceof ChannelIF) {
            ChannelIF channel1 = (ChannelIF) obj1;

            if (obj2 instanceof ChannelIF) {
                ChannelIF channel2 = (ChannelIF) obj2;

                if (!channel1.equals(channel2)) {
                    return CHANNEL_MISMATCH;
                }

                if (channel1.getItems().size() != channel2.getItems().size()) {
                    return CHANNEL_CHANGED;
                }

                Iterator items = channel1.getItems().iterator();

                while (items.hasNext()) {
                    if (!channel2.getItems().contains(items.next())) {
                        return CHANNEL_CHANGED;
                    }
                }

                return CHANNEL_IDENTICAL;

            } else {
                throw new IllegalArgumentException("Not instance of ChannelIF " + obj2);
            }

        } else {
            throw new IllegalArgumentException("Not instance of ChannelIF " + obj1);
        }
    }

}
