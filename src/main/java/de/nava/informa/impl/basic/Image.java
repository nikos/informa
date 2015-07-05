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

import de.nava.informa.core.ImageIF;

import java.net.URL;

/**
 * In-Memory implementation of the ImageIF interface.
 *
 * @author Niko Schmuck (niko@nava.de)
 */
public class Image implements ImageIF {

    private static final long serialVersionUID = 2519227507413286976L;

    private long id;
    private String title;
    private String description;
    private URL location;
    private URL link;
    private int width;
    private int height;

    public Image() {
        this("[Unknown Image]", null, null);
    }

    public Image(String title, URL location, URL link) {
        this.id = IdGenerator.getInstance().getId();
        this.title = title;
        this.location = location;
        this.link = link;
    }

    // --------------------------------------------------------------
    // implementation of ImageIF interface
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getLocation() {
        return location;
    }

    public void setLocation(URL location) {
        this.location = location;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
