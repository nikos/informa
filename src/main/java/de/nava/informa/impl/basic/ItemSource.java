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
import de.nava.informa.core.ItemSourceIF;

import java.util.Date;

/**
 * In-Memory implementation of the ItemSourceIF interface.
 *
 * @author Michael Harhen
 */
public class ItemSource implements ItemSourceIF {

    private static final long serialVersionUID = -7976590108892553322L;

    private ItemIF item;
    private String name;
    private String location;
    private Date timestamp;

    /**
     * Default constructor.
     *
     * @param item the item
     */
    public ItemSource(ItemIF item) {
        this(item, null, null, null);
    }

    public ItemSource(ItemIF item, String name, String location, Date timestamp) {
        this.item = item;
        this.name = name;
        this.location = location;
        this.timestamp = timestamp;
    }
    // --------------------------------------------------------------
    // implementation of ItemSourceIF interface
    // --------------------------------------------------------------

    public ItemIF getItem() {
        return item;
    }

    public void setItem(ItemIF item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
