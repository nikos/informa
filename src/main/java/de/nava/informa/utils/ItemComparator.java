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

import de.nava.informa.core.ItemIF;

import java.util.Comparator;

/**
 * Custom comparator for ItemIF objects, which takes the date the news
 * item was found into account.
 *
 * @author Niko Schmuck
 */
public class ItemComparator implements Comparator<ItemIF> {

    private boolean reverseOrder;
    private boolean useFoundDate;

    /**
     * Default constructor using ascending dates (oldest first) and using
     * the date specified by the item (as opposed to the date the item
     * was found by retrieving).
     */
    public ItemComparator() {
        this(false, false);
    }

    public ItemComparator(boolean reverseOrder) {
        this(reverseOrder, false);
    }

    public ItemComparator(boolean reverseOrder, boolean useFoundDate) {
        this.reverseOrder = reverseOrder;
        this.useFoundDate = useFoundDate;
    }

    public int compare(ItemIF item1, ItemIF item2) {
        int cmp = 0;
        if (useFoundDate) {
            if (item1.getFound() != null && item2.getFound() != null) {
                cmp = item1.getFound().compareTo(item2.getFound());
            }
        } else {
            if (item1.getDate() != null && item2.getDate() != null) {
                cmp = item1.getDate().compareTo(item2.getDate());
            }
        }
        if (reverseOrder) {
            return -1 * cmp;
        } else {
            return cmp;
        }
    }

    public boolean getReverseOrder() {
        return reverseOrder;
    }

    public void setReverseOrder(boolean reverseOrder) {
        this.reverseOrder = reverseOrder;
    }

    public boolean getUseFoundDate() {
        return useFoundDate;
    }

    public void setUseFoundDate(boolean useFoundDate) {
        this.useFoundDate = useFoundDate;
    }

}
