//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils.cleaner;

import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;

/**
 * Matchers answer if the article in the given channel is unwanted.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public interface CleanerMatcherIF {

    /**
     * Invoked by cleaning engine to check given item in given channel for matching some rule.
     *
     * @param item    item to check.
     * @param channel channel where the item is.
     * @return TRUE if item matches the rule.
     */
    boolean isMatching(ItemIF item, ChannelIF channel);
}
