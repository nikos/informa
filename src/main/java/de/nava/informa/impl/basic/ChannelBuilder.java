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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Element;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

/**
 * Factory for the creation of the channel object model with the in-memory
 * implementation.
 *
 * @author Niko Schmuck
 */
public class ChannelBuilder implements ChannelBuilderIF {

    private static Log logger = LogFactory.getLog(ChannelBuilder.class);

    public ChannelBuilder() {
        logger.debug("New channel builder for the in-memory backend");
    }

    // --------------------------------------------------------------
    // implementation of ChannelBuilderIF interface
    // --------------------------------------------------------------

    public void init(Properties props) throws ChannelBuilderException {
        logger.debug("Initialising channel builder for in-memory backend");
    }

    public ChannelGroupIF createChannelGroup(String title) {
        return new ChannelGroup(title);
    }


    public ChannelIF createChannel(String title, String location) {
        Channel c = new Channel(title);
        try {
            c.setLocation(new URL(location));
        } catch (MalformedURLException e) {
            logger.warn("not a valid URL " + location + " for channel " + title);
        }
        return c;
    }

    public ChannelIF createChannel(Element channelElement, String title, String location) {
        // TODO Auto-generated method stub
        return null;
    }

    public ChannelIF createChannel(String title) {
        return new Channel(title);
    }

    public ChannelIF createChannel(Element channelElement, String title) {
        return new Channel(channelElement, title);
    }


    public ItemIF createItem(ChannelIF channel, String title, String description,
                             URL link) {
        return createItem(null, channel, title, description, link);
    }

    public ItemIF createItem(Element itemElement, ChannelIF channel, String title, String description,
                             URL link) {
        ItemIF item = new Item(itemElement, channel, title, description, link);
        if (channel != null) {
            channel.addItem(item);
        }
        return item;
    }

    /**
     * Create an item from an existing item.
     *
     * @param chan    - Channel in which the new item will be held
     * @param oldItem - Old item which will provide the state for the new item
     */
    public ItemIF createItem(ChannelIF chan, ItemIF oldItem) {
        return createItem(null, chan, oldItem.getTitle(), oldItem.getDescription(), oldItem.getLink());
    }

    public ImageIF createImage(String title, URL location, URL link) {
        return new Image(title, location, link);
    }

    public TextInputIF createTextInput(String title, String description, String name, URL link) {
        return new TextInput(title, description, name, link);
    }

    public CloudIF createCloud(String domain, int port, String path, String registerProcedure, String protocol) {
        logger.info("ChannelBuilder is creating a Basic Cloud");
        return new Cloud(domain, port, path, registerProcedure, protocol);
    }

    public ItemSourceIF createItemSource(ItemIF item, String name, String location, Date timestamp) {
        return new ItemSource(item, name, location, timestamp);
    }

    public ItemEnclosureIF createItemEnclosure(ItemIF item, URL location, String type, int length) {
        return new ItemEnclosure(item, location, type, length);
    }

    public ItemGuidIF createItemGuid(ItemIF item, String location, boolean permaLink) {
        return new ItemGuid(item, location, permaLink);
    }

    public CategoryIF createCategory(CategoryIF parent, String title) {
        CategoryIF cat = new Category(title);
        if (parent != null) {
            parent.addChild(cat);
        }
        return cat;
    }

    /**
     * The following methods are only meaningful for persistent informa back end
     * implementations such as Hibernate and are no-ops otherwise.
     */
    public void close() throws ChannelBuilderException {
        logger.debug("Closing channel builder for in-memory backend");
    }

    public void beginTransaction() throws ChannelBuilderException {
        logger.debug("No-op beginTransaction for in-memory backend");
    }

    public void endTransaction() throws ChannelBuilderException {
        logger.debug("No-op endTransaction for in-memory backend");
    }

    public void update(Object obj) {
        logger.debug("No-op update for in-memory backend");
    }

}
