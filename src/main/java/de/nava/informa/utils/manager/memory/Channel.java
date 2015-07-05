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

import de.nava.informa.core.ChannelGroupIF;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Local implementation of <code>ChannelIF</code>.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public class Channel extends de.nava.informa.impl.basic.Channel {

    private static final long serialVersionUID = -2479661776931822761L;
    private List<ChannelGroupIF> groups = new ArrayList<>();

    /**
     * Creates channel object.
     *
     * @param id       ID of the channel.
     * @param title    title of the channel.
     * @param location URL of resource location.
     */
    public Channel(long id, String title, URL location) {
        super(title);
        setId(id);
        setLocation(location);
    }

    /**
     * Adds parent group to the list when channel is assigned to the new group.
     *
     * @param group group to add.
     */
    public final void addParentGroup(ChannelGroupIF group) {
        synchronized (group) {
            if (!groups.contains(group)) {
                groups.add(group);
            }
        }
    }

    /**
     * Removes parent group from the list when channel is unassigned from it.
     *
     * @param group group to remove.
     */
    public final void removeParentGroup(ChannelGroupIF group) {
        synchronized (group) {
            groups.remove(group);
        }
    }

    /**
     * Returns the list of parent groups.
     *
     * @return list of groups.
     */
    public final ChannelGroupIF[] getParentGroups() {
        return groups.toArray(new ChannelGroupIF[groups.size()]);
    }
}
