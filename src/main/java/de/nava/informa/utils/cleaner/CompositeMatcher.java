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

import java.util.List;
import java.util.Vector;

/**
 * Composite matcher follows Composite pattern to combine several matchers
 * and present them as single instance. It uses simple rule to make a decision.
 * If at least one matcher matches the item composite object also returns match.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
class CompositeMatcher implements CleanerMatcherIF {
    private List<CleanerMatcherIF> matchers = new Vector<>();

    /**
     * Invoked by cleaning engine to check given item in given channel for matching some rule.
     *
     * @param item    item to check.
     * @param channel channel where the item is.
     * @return TRUE if item matches the rule.
     */
    public boolean isMatching(ItemIF item, ChannelIF channel) {
        boolean matching = false;

        final int size = matchers.size();
        for (int i = 0; i < size && !matching; i++) {
            CleanerMatcherIF matcher = matchers.get(i);
            matching = matcher.isMatching(item, channel);
        }

        return matching;
    }

    /**
     * Adds new matcher to the list.
     *
     * @param m matcher object.
     */
    public void add(CleanerMatcherIF m) {
        if (!matchers.contains(m)) {
            matchers.add(m);
        }
    }

    /**
     * Removes matcher from the list.
     *
     * @param m matcher object.
     */
    public void remove(CleanerMatcherIF m) {
        matchers.remove(m);
    }
}
