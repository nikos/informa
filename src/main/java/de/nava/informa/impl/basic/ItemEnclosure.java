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

import de.nava.informa.core.ItemEnclosureIF;
import de.nava.informa.core.ItemIF;

import java.net.URL;

/**
 * In-Memory implementation of the ItemSourceIF interface.
 *
 * @author Michael Harhen
 */
public class ItemEnclosure implements ItemEnclosureIF {

    private static final long serialVersionUID = -3607443015281222L;

    private ItemIF item;
    private URL location;
    private int length;
    private String type;

    /**
     * Default constructor.
     *
     * @param item the item.
     */
    public ItemEnclosure(ItemIF item) {
        this(item, null, null, -1);
    }

    public ItemEnclosure(ItemIF item, URL location, String type, int length) {
        this.item = item;
        this.location = location;
        this.type = type;
        this.length = length;
    }
    // --------------------------------------------------------------
    // implementation of ItemEnclosureIF interface
    // --------------------------------------------------------------

    public ItemIF getItem() {
        return item;
    }

    public void setItem(ItemIF item) {
        this.item = item;
    }

    public URL getLocation() {
        return location;
    }

    public void setLocation(URL location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
