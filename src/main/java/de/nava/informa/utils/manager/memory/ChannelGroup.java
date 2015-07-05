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

/**
 * Local implementation of <code>ChannelGroupIF</code>.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public class ChannelGroup extends de.nava.informa.impl.basic.ChannelGroup {

    private static final long serialVersionUID = 6325071875843026078L;

    /**
     * Creates group object.
     *
     * @param id    id to assign.
     * @param title title of group.
     */
    public ChannelGroup(long id, String title) {
        super(id, title);
    }

}
