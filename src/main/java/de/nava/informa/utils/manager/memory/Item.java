//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils.manager.memory;

import de.nava.informa.core.ChannelIF;

/**
 * Local implementation of <code>ItemIF</code>.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public class Item extends de.nava.informa.impl.basic.Item {

    private static final long serialVersionUID = 8291899713409593409L;
    private ChannelIF parent;

    /**
     * Creates item object.
     *
     * @param id     ID of item.
     * @param title  title of item.
     * @param parent parent channel.
     */
    public Item(long id, String title, ChannelIF parent) {
        setId(id);
        setTitle(title);
        setParent(parent);
    }

    /**
     * Returns parent channel of this item.
     *
     * @return parent channel.
     */
    public final ChannelIF getParent() {
        return parent;
    }

    /**
     * Sets parent channel of this item.
     *
     * @param parent parent channel.
     */
    public final void setParent(ChannelIF parent) {
        this.parent = parent;
    }
}
