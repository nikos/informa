//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils.manager;

import de.nava.informa.core.ChannelGroupIF;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;

import java.net.URL;

/**
 * General interface for all persistence managers. This interface defines methods, which can be
 * safely used by client application to perform changes over persistent data.
 * <p>
 * Main assumption is that client application operates with objects (instances) created <b>ONLY</b>
 * by manager instance. Some persistence implementations might be very sensitive to duplicate
 * objects representing the same data in persistent storage.
 * <p>
 * <b>Please take care about thread-safety of your implementation.</b> Single instance will
 * be shared by <code>PersistenceManagerConfig</code> through whole application.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public interface PersistenceManagerIF {
    /**
     * Creates new group of channels in persistent storage.
     *
     * @param title title of the group.
     * @return initialized and persisted group object.
     * @throws PersistenceManagerException in case of any problems.
     */
    ChannelGroupIF createGroup(String title)
            throws PersistenceManagerException;

    /**
     * Updates data in storage with data from the group object.
     *
     * @param group group object
     * @throws PersistenceManagerException in case of any problems.
     */
    void updateGroup(ChannelGroupIF group)
            throws PersistenceManagerException;

    /**
     * Deletes group from persistent storage.
     *
     * @param group group to delete.
     * @throws PersistenceManagerException in case of any problems.
     */
    void deleteGroup(ChannelGroupIF group)
            throws PersistenceManagerException;

    /**
     * Takes channels from the <code>second</code> group and put them all in <code>first</code>
     * group. Then <code>second</code> group is deleted.
     *
     * @param first  first group of channels.
     * @param second second group of channels.
     * @throws PersistenceManagerException in case of any problems.
     */
    void mergeGroups(ChannelGroupIF first, ChannelGroupIF second)
            throws PersistenceManagerException;

    /**
     * Returns the list of groups available in database.
     *
     * @return list of groups.
     * @throws PersistenceManagerException in case of any problems.
     */
    ChannelGroupIF[] getGroups()
            throws PersistenceManagerException;

    /**
     * Creates new channel object and persists it into storage.
     *
     * @param title    title of the channel.
     * @param location location of channel data resource.
     * @return newly created object.
     * @throws PersistenceManagerException in case of any problems.
     */
    ChannelIF createChannel(String title, URL location)
            throws PersistenceManagerException;

    /**
     * Updates data in database with data from channel object.
     *
     * @param channel channel object.
     * @throws PersistenceManagerException in case of any problems.
     */
    void updateChannel(ChannelIF channel)
            throws PersistenceManagerException;

    /**
     * Adds <code>channel</code> to the <code>group</code>.
     *
     * @param channel channel to add.
     * @param group   group to use.
     * @throws PersistenceManagerException in case of any problems.
     */
    void addChannelToGroup(ChannelIF channel, ChannelGroupIF group)
            throws PersistenceManagerException;

    /**
     * Deletes <code>channel</code> from the <code>group</code>.
     * This method doesn't delete channel from persistent storage. It only
     * breaks the association between channel and group.
     *
     * @param channel channel to delete.
     * @param group   group to use.
     * @throws PersistenceManagerException in case of any problems.
     */
    void removeChannelFromGroup(ChannelIF channel, ChannelGroupIF group)
            throws PersistenceManagerException;

    /**
     * Deletes channel from persistent storage.
     *
     * @param channel channel to delete.
     * @throws PersistenceManagerException in case of any problems.
     */
    void deleteChannel(ChannelIF channel)
            throws PersistenceManagerException;

    /**
     * Creates new item in the channel.
     *
     * @param channel channel to put new item into.
     * @param title   title of new item.
     * @return new item object.
     * @throws PersistenceManagerException in case of any problems.
     */
    ItemIF createItem(ChannelIF channel, String title)
            throws PersistenceManagerException;

    /**
     * Creates new item using specified object as ethalon.
     * <b>Note that application <i>could</i> already add object to the channel and
     * only persistent modifications required.</b>
     *
     * @param channel channel to put new item into.
     * @param ethalon object to copy properties values from.
     * @return new item object.
     * @throws PersistenceManagerException in case of any problems.
     */
    ItemIF createItem(ChannelIF channel, ItemIF ethalon)
            throws PersistenceManagerException;

    /**
     * Updates data in database with data from item object.
     *
     * @param item item object.
     * @throws PersistenceManagerException in case of any errors.
     */
    void updateItem(ItemIF item)
            throws PersistenceManagerException;

    /**
     * Deletes the item from the persistent storage.
     *
     * @param item item to delete.
     * @throws PersistenceManagerException in case of any problems.
     */
    void deleteItem(ItemIF item)
            throws PersistenceManagerException;
}
