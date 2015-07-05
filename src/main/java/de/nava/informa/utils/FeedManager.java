//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils;

import de.nava.informa.core.ChannelBuilderIF;
import de.nava.informa.core.ChannelUpdatePeriod;
import de.nava.informa.core.FeedIF;
import de.nava.informa.core.ParseException;
import de.nava.informa.parsers.OPMLParser;

import java.io.IOException;
import java.util.*;

/**
 * A class used to manage feeds. Feeds are parsed and stored using the
 * <code>addFeed</code> method. Subsequent requests for the same feed URI
 * (using either <code>addFeed</code> or <code>getFeed</code>) will return
 * a cached copy, unless the feed is due to be refreshed direct from the source.
 * <br>
 * The time before a feed is considered out of date will be read from the feed
 * if possible, failing that a default UpdatePeriod and UpdateFrequency are
 * used. <br>
 *
 * @author Sam Newman
 */
public class FeedManager {

    /**
     * Default channel builder used if no explicit channel builder was set
     */
    private static final ChannelBuilderIF DEFAULT_BUILDER = new de.nava.informa.impl.basic.ChannelBuilder();

    private ChannelBuilderIF channelBuilder;

    private ChannelUpdatePeriod defaultUpdatePeriod;

    private int defaultUpdateFrequency;

    /**
     * Internal store of FeedEntry's, keyed by feed uri
     */
    private Map<String, FeedManagerEntry> feeds;

    /**
     * cache settings variable
     */
    private CacheSettingsIF cacheSettings = new CacheSettings();

    /**
     * feed Daemon
     */
    private FeedRefreshDaemon refreshDaemon = new FeedRefreshDaemon();

    /**
     * Creates a new FeedManager object.
     */
    public FeedManager() {
        feeds = new HashMap<>();
        this.defaultUpdatePeriod = ChannelUpdatePeriod.UPDATE_DAILY;
        this.defaultUpdateFrequency = 1;
        setCacheSettings(defaultUpdatePeriod, defaultUpdateFrequency);
    }

    /**
     * Creates a new FeedManager object.
     */
    public FeedManager(ChannelUpdatePeriod defaultUpdatePeriod, int defaultUpdateFrequency) {
        feeds = new HashMap<>();
        this.defaultUpdatePeriod = defaultUpdatePeriod;
        this.defaultUpdateFrequency = defaultUpdateFrequency;
        setCacheSettings(defaultUpdatePeriod, defaultUpdateFrequency);
    }

    /**
     * Initialisation of the cacheSettings private variable
     */
    private void setCacheSettings(ChannelUpdatePeriod updatePeriod, int updateFrequency) {
        // TODO refactoring constants declarations
        long MILLISECONDS_IN_HOUR = 3600000L;
        long MILLISECONDS_IN_DAY = 86400000L;
        long MILLISECONDS_IN_MONTH = 2419200000L;
        long MILLISECONDS_IN_YEAR = 31536000000L;

        long msInPeriod;

        if (updatePeriod.equals(ChannelUpdatePeriod.UPDATE_HOURLY)) {
            msInPeriod = MILLISECONDS_IN_HOUR;
        } else if (updatePeriod.equals(ChannelUpdatePeriod.UPDATE_DAILY)) {
            msInPeriod = MILLISECONDS_IN_DAY;
        } else if (updatePeriod.equals(ChannelUpdatePeriod.UPDATE_MONTHLY)) {
            msInPeriod = MILLISECONDS_IN_MONTH;
        } else if (updatePeriod.equals(ChannelUpdatePeriod.UPDATE_YEARLY)) {
            msInPeriod = MILLISECONDS_IN_YEAR;
        } else {
            throw new IllegalArgumentException("updatePeriod " + updatePeriod
                    + " is not valid");
        }
        this.cacheSettings.setDefaultTtl(msInPeriod / updateFrequency);
    }

    /**
     * Loads a feed given the metadata information contained in the given FeedIF
     * object
     */
    public FeedIF addFeed(FeedIF feed) throws FeedManagerException {
        return addFeed(feed.getLocation().toString());
    }

    /**
     * Loads a feed given the metadata information contained in the given FeedIF
     * object
     *
     * @param ttl  in minutes
     * @return FeedIF object
     * @throws FeedManagerException DOCUMENT ME!
     */
    public FeedIF addFeed(FeedIF feed, int ttl) throws FeedManagerException {
        return addFeed(feed.getLocation().toString(), ttl);
    }

    /**
     * Attempts to load the feeds specified in the given OPML file into the
     * manager
     *
     * @param opmlFeedUri An OPML file containing a list of feeds
     * @return A list of FeedIF files representing the feeds added
     * @throws FeedManagerException DOCUMENT ME!
     */
    public Collection<FeedIF> addFeeds(String opmlFeedUri) throws FeedManagerException {
        Collection<FeedIF> retFeeds;

        try {
            Collection feedsColl = OPMLParser.parse(opmlFeedUri);
            retFeeds = new ArrayList<>();

            for (Object aFeedsColl : feedsColl) {
                FeedIF element = (FeedIF) aFeedsColl;
                retFeeds.add(addFeed(element));
            }
        } catch (IOException | ParseException e) {
            throw new FeedManagerException(e);
        }

        return retFeeds;
    }

    /**
     * Attempts to load the feeds specified in the given OPML file into the
     * manager.
     *
     * @param opmlFeedUri An OPML file containing a list of feeds
     * @param ttl         a ttl for all feeds (in minutes)
     * @return A list of FeedIF files representing the feeds added
     * @throws FeedManagerException DOCUMENT ME!
     */
    public Collection<FeedIF> addFeeds(String opmlFeedUri, int ttl)
            throws FeedManagerException {
        Collection<FeedIF> retFeeds;

        try {
            Collection feedsColl = OPMLParser.parse(opmlFeedUri);
            retFeeds = new ArrayList<>();

            for (Object aFeedsColl : feedsColl) {
                FeedIF element = (FeedIF) aFeedsColl;
                retFeeds.add(addFeed(element, ttl));
            }
        } catch (IOException | ParseException e) {
            throw new FeedManagerException(e);
        }

        return retFeeds;
    }

    /**
     * Sets the channel builder used when reading the news feeds.
     */
    public void setChannelBuilder(ChannelBuilderIF chBuilder) {
        channelBuilder = chBuilder;
    }

    /**
     * Gets the channel builder used by the feed entries.
     */
    public ChannelBuilderIF getChannelBuilder() {
        if (channelBuilder == null) {
            return DEFAULT_BUILDER;
        } else {
            return channelBuilder;
        }
    }

    /**
     * Determines if the manager is handling the feed
     *
     * @param feedUri The URI for the feed
     * @return true if the feed is managed, else false
     */
    public boolean hasFeed(String feedUri) {
        return feeds.containsKey(feedUri);
    }

    /**
     * Adds the specified feed to the manager. If the feed is already managed
     * nothing happens
     *
     * @param feedUri The URI of the feed to add
     * @return The feed being managed
     * @throws FeedManagerException If the feed specified is invalid
     */
    public FeedIF addFeed(String feedUri) throws FeedManagerException {
        if (!hasFeed(feedUri)) {
            FeedManagerEntry fme = new FeedManagerEntry(feedUri, getChannelBuilder(),
                    defaultUpdatePeriod, defaultUpdateFrequency);
            feeds.put(feedUri, fme);
            refreshDaemon.addFeed(fme);
        }

        return getFeed(feedUri);
    }

    /**
     * Adds the specified feed to the manager with a specific Ttl. The ttl
     * specified may superseded the global ttl (deducted from
     * <code>updatePeriod</code> and <code>updateFrequency</code>), depending
     * on the feed type. This behavior is implemented in the differents
     * <code>CacheSettingsIF</code> implementations provided.
     *
     * @param feedUri       The URI of the feed to add
     * @param wantedTtlmins The ttl wanted for this feed (in minutes)
     * @return The feed being managed
     * @throws FeedManagerException If the feed specified is invalid
     * @see de.nava.informa.utils.CacheSettingsIF
     */
    public FeedIF addFeed(String feedUri, int wantedTtlmins)
            throws FeedManagerException {
        if (!hasFeed(feedUri)) {
            FeedManagerEntry FMEntry = new FeedManagerEntry(feedUri,
                    getChannelBuilder(), defaultUpdatePeriod, defaultUpdateFrequency);

            if (wantedTtlmins > 0) {
                FMEntry.setWantedTtl(wantedTtlmins * (60 * 1000));
            }
            feeds.put(feedUri, FMEntry);
            refreshDaemon.addFeed(FMEntry);
        } else {
            // TODO : what behavior when the ttl is different ?
        }

        return getFeed(feedUri);
    }

    /**
     * Removes the specified feed from the manager
     *
     * @param feedUri The URI for the feed to remove
     */
    public void removeFeed(String feedUri) {
        feeds.remove(feedUri);
    }

    /**
     * Retrieves the feed being managed. Note that null will be returned if the
     * feed is not being managed. If you want to add a feed and return it in the
     * same method, use <code>addFeed</code> instead.
     *
     * @param feedUri The feed to return
     * @return The feed being managed, or null if the feed is not present
     * @throws FeedManagerException If the feed specified is invalid
     */
    public FeedIF getFeed(String feedUri) throws FeedManagerException {
        FeedIF feed = null;

        if (hasFeed(feedUri)) {
            FeedManagerEntry entry = feeds.get(feedUri);
            feed = entry.getFeed();
        }

        return feed;
    }

}