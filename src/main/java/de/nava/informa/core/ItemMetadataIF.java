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

import java.io.Serializable;

/**
 * Implementing class holds information about the belonging news item.
 * This metadata contains the read status and what the score (priority
 * level for display, may be used to sort items by their relevance) of
 * the item is.
 *
 * @author Niko Schmuck (niko@nava.de)
 */
public interface ItemMetadataIF extends Serializable {

    public static final int DEFAULT_SCORE = 100;

    ItemIF getItem();

    void setItem(ItemIF item);

    boolean isMarkedRead();

    void setMarkedRead(boolean markedRead);

    int getScore();

    void setScore(int score);

}
