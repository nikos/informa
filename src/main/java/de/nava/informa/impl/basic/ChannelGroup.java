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

import de.nava.informa.core.ChannelGroupIF;
import de.nava.informa.core.ChannelIF;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * In-Memory implementation of the ChannelGroupIF interface.
 *
 * @author Niko Schmuck
 */
public class ChannelGroup implements ChannelGroupIF {

    private static final long serialVersionUID = -4037744833783193972L;

    private long id;
    private String title;
    private Collection<ChannelIF> channels;
    private ChannelGroupIF parent;
    private List<ChannelGroupIF> children;

    public ChannelGroup() {
        this("[Unknown title]");
    }

    public ChannelGroup(String title) {
        this(IdGenerator.getInstance().getId(), null, title);
    }

    public ChannelGroup(long id, String title) {
        this(id, null, title);
    }

    public ChannelGroup(long id, ChannelGroupIF parent, String title) {
        this.id = id;
        this.title = title;
        this.channels = new ArrayList<>();
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    // --------------------------------------------------------------
    // implementation of ChannelGroupIF interface
    // --------------------------------------------------------------

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void add(ChannelIF channel) {
        channels.add(channel);
    }

    public void remove(ChannelIF channel) {
        channels.remove(channel);
    }

    public Collection<ChannelIF> getAll() {
        return channels;
    }

    public ChannelIF getById(long id) {
        Iterator it = channels.iterator();
        while (it.hasNext()) {
            ChannelIF channel = (ChannelIF) it.next();
            if (channel.getId() == id) {
                return channel;
            }
        }
        return null;
    }

    public ChannelGroupIF getParent() {
        return parent;
    }

    public void setParent(ChannelGroupIF parent) {
        this.parent = parent;
    }

    public Collection getChildren() {
        return children;
    }

    public void addChild(ChannelGroupIF child) {
        children.add(child);
        child.setParent(this);
    }

    public void removeChild(ChannelGroupIF child) {
        children.remove(child);
    }

    // ----------------------------------------------------------------------
    // overwrite default method implementation from Object
    // ----------------------------------------------------------------------

    public String toString() {
        return "[ChannelGroup (" + id + ")]";
    }

}
