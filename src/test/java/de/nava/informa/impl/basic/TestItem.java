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
import junit.framework.TestCase;

import java.net.URL;
import java.util.Collection;
import java.util.Date;

/**
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 * @see Item
 */
public class TestItem extends TestCase {

    /**
     * @throws Exception in case of any exceptions
     * @see de.nava.informa.core.ItemIF#equals
     */
    public void testHowDifferentImplementationsMatching() throws Exception {
        ItemIF item1, item2;

        item1 = new Item("a", "b", new URL("file://a"));
        item2 = new SimpleItem("a", "b", new URL("file://a"));
        assertTrue(item1.equals(item2));

        item1 = new Item("a", "b", new URL("file://a"));
        item2 = new SimpleItem("a", "b", new URL("file://b"));
        assertFalse(item1.equals(item2));

        item1 = new Item("a", "b", new URL("file://a"));
        item2 = new SimpleItem("a", "b", new URL("file://A"));
        assertTrue(item1.equals(item2));
    }

    /**
     * Simple implementation of {@link de.nava.informa.core.ItemIF} interface which is supporting
     * only <code>title</code>, <code>description</code> and <code>link</code> setting.
     */
    protected static class SimpleItem implements ItemIF {
        private static final long serialVersionUID = 5381901818020147855L;

        private String title;
        private String description;
        private URL link;

        public SimpleItem(String title, String description, URL link) {
            setTitle(title);
            setDescription(description);
            setLink(link);
        }

        public ItemGuidIF getGuid() {
            return null;
        }

        public void setGuid(ItemGuidIF guid) {
        }

        public URL getComments() {
            return null;
        }

        public void setComments(URL comments) {
        }

        public ItemSourceIF getSource() {
            return null;
        }

        public void setSource(ItemSourceIF source) {
        }

        public ItemEnclosureIF getEnclosure() {
            return null;
        }

        public void setEnclosure(ItemEnclosureIF enclosure) {
        }

        public String getSubject() {
            return null;
        }

        public void setSubject(String subject) {
        }

        public Date getDate() {
            return null;
        }

        public void setDate(Date date) {
        }

        public Date getFound() {
            return null;
        }

        public void setFound(Date found) {
        }

        public long getId() {
            return 0;
        }

        public void setId(long id) {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getElementValue(String path) {
            return null;
        }

        public String[] getElementValues(String path, String[] elements) {
            return new String[0];
        }

        public String getAttributeValue(String path, String attribute) {
            return null;
        }

        public String[] getAttributeValues(String path, String[] attributes) {
            return new String[0];
        }

        public String getCreator() {
            return null;
        }

        public void setCreator(String creator) {
        }

        public Collection getCategories() {
            return null;
        }

        public void setCategories(Collection categories) {
        }

        public void addCategory(CategoryIF category) {
        }

        public void removeCategory(CategoryIF category) {
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public URL getLink() {
            return link;
        }

        public void setLink(URL link) {
            this.link = link;
        }

        public ChannelIF getChannel() {
            return null;
        }

        public void setChannel(ChannelIF channel) {
        }

        public boolean getUnRead() {
            return false;
        }

        public void setUnRead(boolean val) {
        }
    }
}
