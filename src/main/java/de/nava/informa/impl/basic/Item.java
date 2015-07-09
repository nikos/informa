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

import de.nava.informa.core.*;
import de.nava.informa.utils.XmlPathUtils;
import org.jdom2.Element;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * In-Memory implementation of the ItemIF interface.
 *
 * @author Niko Schmuck
 */
public class Item implements ItemIF {

    private static final long serialVersionUID = -8346101720187629760L;

    private long id;
    private String title;
    private String description;
    private URL link;
    private Collection<CategoryIF> categories;
    private String creator;
    private String subject;
    private Date date;
    private Date found;
    private ItemGuidIF guid;
    private URL comments;
    private ItemSourceIF source;
    private ItemEnclosureIF enclosure;
    private Element itemElement;
    private ChannelIF channel;
    private boolean unRead;

    public Item() {
        this(null, null, "[Unknown Item]", null, null);
    }

    public Item(String title, String description, URL link) {
        this(null, null, title, description, link);
    }

    public Item(ChannelIF channel, String title, String description, URL link) {
        this(null, channel, title, description, link);
    }

    public Item(Element itemElement, String title, String description, URL link) {
        this(itemElement, null, title, description, link);
    }

    public Item(Element itemElement, ChannelIF channel, String title, String description, URL link) {
        this.id = IdGenerator.getInstance().getId();
        this.itemElement = itemElement;
        this.channel = channel;
        this.title = title;
        this.description = description;
        this.link = link;
        this.categories = new ArrayList<>();
        this.unRead = true;
        unRead = true;
    }

    // --------------------------------------------------------------
    // implementation of ItemIF interface
    // --------------------------------------------------------------

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ChannelIF getChannel() {
        return channel;
    }

    public void setChannel(ChannelIF channel) {
        this.channel = channel;
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

    public boolean getUnRead() {
        return unRead;
    }

    public void setUnRead(boolean val) {
        unRead = val;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public Collection getCategories() {
        return categories;
    }

    public void setCategories(Collection<CategoryIF> categories) {
        this.categories = categories;
    }

    public void addCategory(CategoryIF category) {
        categories.add(category);
    }

    public void removeCategory(CategoryIF category) {
        categories.remove(category);
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getFound() {
        return found;
    }

    public void setFound(Date found) {
        this.found = found;
    }

    public ItemGuidIF getGuid() {
        return guid;
    }

    public void setGuid(ItemGuidIF guid) {
        this.guid = guid;
    }

    public URL getComments() {
        return comments;
    }

    public void setComments(URL comments) {
        this.comments = comments;
    }

    public ItemSourceIF getSource() {
        return source;
    }

    public void setSource(ItemSourceIF source) {
        this.source = source;
    }

    public ItemEnclosureIF getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(ItemEnclosureIF enclosure) {
        this.enclosure = enclosure;
    }

    public String getElementValue(final String path) {
        return XmlPathUtils.getElementValue(itemElement, path);
    }

    public String[] getElementValues(final String path, final String[] elements) {
        return XmlPathUtils.getElementValues(itemElement, path, elements);
    }

    public String getAttributeValue(final String path, final String attribute) {
        return XmlPathUtils.getAttributeValue(itemElement, path, attribute);
    }

    public String[] getAttributeValues(final String path, final String[] attributes) {
        return XmlPathUtils.getAttributeValues(itemElement, path, attributes);
    }

    // ----------------------------------------------------------------------
    // overwrite default method implementation from Object
    // ----------------------------------------------------------------------

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return <code>true</code> if this object is the same as the obj
     * argument; <code>false</code> otherwise.
     * <p>
     * NOTE: Please keep this code in sync with de.nava.informa.impl.hibernate.Item.equals().
     * @see #hashCode()
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemIF)) return false;

        final ItemIF item = (ItemIF) o;

        final String itemTitle = item.getTitle();
        if (title != null
                ? !title.equals(itemTitle)
                : itemTitle != null) return false;

        // Comparison of links uses synchronized code of Java-NET.
        // This may hurt multi-threaded applications. So, please think twice
        // before using direct comparison of links.
        final URL itemLink = item.getLink();
        if (link != null
                ? itemLink == null || !link.toString().equalsIgnoreCase(itemLink.toString())
                : itemLink != null) return false;

        final String itemDescription = item.getDescription();
        return !(description != null
                ? !description.equals(itemDescription)
                : itemDescription != null);
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hashtables such as those provided by
     * <code>java.util.Hashtable</code>.
     * <p>
     * NOTE: Please keep this code in sync with de.nava.informa.impl.hibernate.Item.hashCode().
     *
     * @return a hash code value for this object.
     */
    public int hashCode() {
        return (title + description + link).hashCode();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    public String toString() {
        return "[Item (" + id + "): " + title + "]";
    }

}
