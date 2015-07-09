//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.core;

import org.jdom2.Element;

import java.net.URL;
import java.util.Date;
import java.util.Properties;

/**
 * This interface allows the channel generators (like a parser) to
 * create the channel object model independent from which
 * implementation is used (in-memory or persistent store).
 *
 * @author Niko Schmuck
 */
public interface ChannelBuilderIF {

    /**
     * Sets the runtime properties defined for this channel builder.
     * This method will be invoked by the ChannelBuilderFactory when the
     * ChannelBuilder is first created.
     *
     * @param props The parsed set of properties which may be applied to
     *              this object.
     * @throws ChannelBuilderException If the initialisation fails for
     *                                 some reason.
     */
    void init(Properties props) throws ChannelBuilderException;

    ChannelGroupIF createChannelGroup(String title);

    ChannelIF createChannel(String title);

    ChannelIF createChannel(String title, String location);

    ChannelIF createChannel(Element channelElement, String title);

    ChannelIF createChannel(Element channelElement, String title, String location);


    /**
     * Creates a news item and assign it to the given channel.
     */
    ItemIF createItem(ChannelIF channel, String title, String description, URL link);

    ItemIF createItem(Element itemElement, ChannelIF channel, String title, String description, URL link);

    ItemIF createItem(ChannelIF channel, ItemIF item);

    ImageIF createImage(String title, URL location, URL link);

    TextInputIF createTextInput(String title, String description, String name, URL link);

    ItemSourceIF createItemSource(ItemIF item, String name, String location, Date timestamp);

    ItemEnclosureIF createItemEnclosure(ItemIF item, URL location, String type, int length);

    ItemGuidIF createItemGuid(ItemIF item, String location, boolean permaLink);

    CloudIF createCloud(String domain, int port, String path, String registerProcedure, String protocol);

    CategoryIF createCategory(CategoryIF parent, String title);

    /**
     * Closes the builder. After this call, all references to any channel objects
     * provided by this ChannelBuilder are invalidated.
     * <p>
     * If the ChannelBuilder has a connection to a persistent store such as a
     * database, connections may be closed.
     */
    void close() throws ChannelBuilderException;

    void beginTransaction() throws ChannelBuilderException;

    void endTransaction() throws ChannelBuilderException;

    void update(Object o) throws ChannelBuilderException;

}
