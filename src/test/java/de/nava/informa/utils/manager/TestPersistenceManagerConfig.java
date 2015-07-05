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
import junit.framework.TestCase;

import java.net.URL;

/**
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 * @see PersistenceManagerConfig
 */
public class TestPersistenceManagerConfig extends TestCase {
    /**
     * @see PersistenceManagerConfig#getPersistenceManager
     * @see PersistenceManagerConfig#setPersistenceManagerClassName
     */
    public void testGetPersistenceManager() {
        // JVM property isn't set - so the manager shouldn't be initialized by default.
        assertNull(PersistenceManagerConfig.getPersistenceManager());

        // Set the name of non-existing class and catch the exception.
        try {
            PersistenceManagerConfig.setPersistenceManagerClassName("badname");
            fail("ClassNotFountException should be thrown.");
        } catch (ClassNotFoundException e) {
            // expected behavior
        } catch (Exception e) {
            fail("ClassNotFountException should be thrown.");
        }

        // Set the name of existing implementation class.
        final String className = DummyPersistenceManager.class.getName();
        try {
            PersistenceManagerConfig.setPersistenceManagerClassName(className);
            assertTrue(PersistenceManagerConfig.getPersistenceManager()
                    instanceof DummyPersistenceManager);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected exception.");
        }
    }

    /**
     * Empty implementation for test purpose only.
     */
    public static class DummyPersistenceManager implements PersistenceManagerIF {
        /**
         * Creates new group of channels in persistent storage.
         *
         * @param title title of the group.
         * @return initialized and persisted group object.
         */
        public ChannelGroupIF createGroup(String title) {
            return null;
        }

        /**
         * Updates data in storage with data from the group object.
         *
         * @param group group object
         */
        public void updateGroup(ChannelGroupIF group) {
        }

        /**
         * Deletes group from persistent storage.
         *
         * @param group group to delete.
         */
        public void deleteGroup(ChannelGroupIF group) {
        }

        /**
         * Takes channels from the <code>second</code> group and put them all in <code>first</code>
         * group. Then <code>second</code> group is deleted.
         *
         * @param first  first group of channels.
         * @param second second group of channels.
         */
        public void mergeGroups(ChannelGroupIF first, ChannelGroupIF second) {
        }

        /**
         * Returns the list of groups available in database.
         *
         * @return list of groups.
         */
        public ChannelGroupIF[] getGroups() {
            return new ChannelGroupIF[0];
        }

        /**
         * Creates new channel object and persists it into storage.
         *
         * @param title    title of the channel.
         * @param location location of channel data resource.
         * @return newly created object.
         */
        public ChannelIF createChannel(String title, URL location) {
            return null;
        }

        /**
         * Updates data in database with data from channel object.
         *
         * @param channel channel object.
         */
        public void updateChannel(ChannelIF channel) {
        }

        /**
         * Adds <code>channel</code> to the <code>group</code>.
         *
         * @param channel channel to add.
         * @param group   group to use.
         */
        public void addChannelToGroup(ChannelIF channel, ChannelGroupIF group) {
        }

        /**
         * Deletes <code>channel</code> from the <code>group</code>.
         * This method doesn't delete channel from persistent storage. It only
         * breaks the association between channel and group.
         *
         * @param channel channel to delete.
         * @param group   group to use.
         */
        public void removeChannelFromGroup(ChannelIF channel, ChannelGroupIF group) {
        }

        /**
         * Deletes channel from persistent storage.
         *
         * @param channel channel to delete.
         */
        public void deleteChannel(ChannelIF channel) {
        }

        /**
         * Creates new item in the channel.
         *
         * @param channel channel to put new item into.
         * @param title   title of new item.
         * @return new item object.
         */
        public ItemIF createItem(ChannelIF channel, String title) {
            return null;
        }

        /**
         * Creates new item using specified object as ethalon.
         *
         * @param channel channel to put new item into.
         * @param ethalon object to copy properties values from.
         * @return new item object.
         */
        public ItemIF createItem(ChannelIF channel, ItemIF ethalon) {
            return null;
        }

        /**
         * Updates data in database with data from item object.
         *
         * @param item item object.
         */
        public void updateItem(ItemIF item) {
        }

        /**
         * Deletes the item from the persistent storage.
         *
         * @param item item to delete.
         */
        public void deleteItem(ItemIF item) {
        }
    }
}
