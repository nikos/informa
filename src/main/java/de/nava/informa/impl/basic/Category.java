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

import de.nava.informa.core.CategoryIF;

import java.util.ArrayList;
import java.util.Collection;

/**
 * In-Memory implementation of the CategoryIF interface.
 *
 * @author Niko Schmuck (niko@nava.de)
 */
public class Category implements CategoryIF {

    private static final long serialVersionUID = 8319888961720961902L;

    private long id;
    private String title;
    private String domain;
    private CategoryIF parent;
    private Collection<CategoryIF> children;

    public Category() {
        this("[Unnamed Category]");
    }

    public Category(String title) {
        this(null, title);
    }

    public Category(CategoryIF parent, String title) {
        this.id = IdGenerator.getInstance().getId();
        this.title = title;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    // --------------------------------------------------------------
    // implementation of CategoryIF interface
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public CategoryIF getParent() {
        return parent;
    }

    public void setParent(CategoryIF parent) {
        this.parent = parent;
    }

    public Collection<CategoryIF> getChildren() {
        return children;
    }

    public void addChild(CategoryIF child) {
        children.add(child);
        child.setParent(this);
    }

    public void removeChild(CategoryIF child) {
        children.remove(child);
    }

    // --------------------------------------------------------------
    // overwrite default method implementation from Object
    // --------------------------------------------------------------

    public boolean equals(Object obj) {
        if (!(obj instanceof CategoryIF)) {
            return false;
        }
        CategoryIF cmp = (CategoryIF) obj;

        return cmp.getTitle().equals(title)
                && (cmp.getId() == id);
    }

    public int hashCode() {
        return title.hashCode() + new Long(id).hashCode();
    }

    public String toString() {
        return "[Category (" + id + "): " + title + "]";
    }

}
