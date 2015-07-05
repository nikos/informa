//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.impl.basic;

import de.nava.informa.core.ItemIF;
import de.nava.informa.core.ItemMetadataIF;

/**
 * In-Memory implementation of the ItemMetadataIF interface.
 *
 * @author Niko Schmuck (niko@nava.de)
 */
public class ItemMetadata implements ItemMetadataIF {

    private static final long serialVersionUID = 4040313829062553182L;

    private ItemIF item;
    private boolean markedRead;
    private int score;

    /**
     * Default constructor which sets this metadata to unread and to
     * the default score (see
     * {@link de.nava.informa.core.ItemMetadataIF#DEFAULT_SCORE}).
     *
     * @param item the item.
     */
    public ItemMetadata(ItemIF item) {
        this.item = item;
        this.markedRead = false;
        this.score = DEFAULT_SCORE;
    }

    // --------------------------------------------------------------
    // implementation of ItemMetadataIF interface
    // --------------------------------------------------------------

    public ItemIF getItem() {
        return item;
    }

    public void setItem(ItemIF item) {
        this.item = item;
    }

    public boolean isMarkedRead() {
        return markedRead;
    }

    public void setMarkedRead(boolean markedRead) {
        this.markedRead = markedRead;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
