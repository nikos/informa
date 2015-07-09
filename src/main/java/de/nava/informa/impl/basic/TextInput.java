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

import de.nava.informa.core.TextInputIF;

import java.net.URL;

/**
 * In-Memory implementation of the TextInputIF interface.
 *
 * @author Niko Schmuck
 */
public class TextInput implements TextInputIF {

    private static final long serialVersionUID = -2953653427729059796L;

    private long id;
    private String title;
    private String description;
    private String name;
    private URL link;

    public TextInput() {
        this("[Unknown TextInput]", null, null, null);
    }

    public TextInput(String title, String description, String name, URL link) {
        this.id = IdGenerator.getInstance().getId();
        this.title = title;
        this.description = description;
        this.name = name;
        this.link = link;
    }

    // --------------------------------------------------------------
    // implementation of TextInputIF interface
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

}
