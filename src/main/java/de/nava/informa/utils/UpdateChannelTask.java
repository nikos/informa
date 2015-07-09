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

import de.nava.informa.core.*;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.parsers.FeedParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.TimerTask;

/**
 * Class for performing a channel retrieval in periodic intervals as a
 * TimerTask. The existing items of a channel are compared to the
 * items contained in the newly gotten channel, and if there are any
 * new items they are appended.
 *
 * @author Niko Schmuck
 */
public class UpdateChannelTask extends TimerTask {

    private static Log logger = LogFactory.getLog(UpdateChannelTask.class);

    private ChannelRegistry registry;
    private ChannelIF channel;
    private ChannelBuilderIF builder;
    private UpdateChannelInfo info;
    private ChannelBuilderIF tempBuilder;

    public UpdateChannelTask(
            ChannelRegistry registry,
            ChannelBuilderIF builder,
            ChannelIF channel,
            UpdateChannelInfo info) {
        this.registry = registry;
        this.channel = channel;
        this.builder = builder;
        this.info = info;

        // builder that is passed in on construction *may* be for persistent Channels.
        // tempBuilder is forced to be memory only so it will not be persisted.
        tempBuilder = new ChannelBuilder();
    }

    public void run() {
        logger.info("Task Run()");
        Thread.currentThread().setName("Informa Update Channel Task");
        /**
         * ChannelBuilder is not re-entrant and it is shared by all the
         * UpdateChannelTasks which are created by single ChannelRegistry.
         * Note that all the beginTransaction() must have a corresponding endTransaction()
         */
        synchronized (builder) {
            if (!info.getFormatDetected())
            /**
             * If this is the first time we see this Channel, then we will now attempt
             * to parse it and if this works we remember the format and proceed.
             * Otherwise we trigger error case handling and eventually deactivate it.
             */ {
                try {
                    builder.beginTransaction();
                    ChannelFormat format =
                            FormatDetector.getFormat(channel.getLocation());
                    channel.setFormat(format);
                    info.setFormatDetected(true);
                    channel.setLastUpdated(new Date());
                    builder.endTransaction();
                } catch (UnsupportedFormatException ex) {
                    logger.info("Unsupported format for Channel");
                    incrementProblems(ex);
                    return;
                } catch (IOException ioe) {
                    logger.info("Cannot retrieve Channel");
                    incrementProblems(ioe);
                    return;
                } catch (ChannelBuilderException e) {
                    e.printStackTrace();
                }
            }
            try {
                synchronized (channel) {
                    builder.beginTransaction();
                    ChannelIF tempChannel =
                            FeedParser.parse(tempBuilder, channel.getLocation());
                    logger.info(
                            "Updating channel from "
                                    + channel.getLocation()
                                    + ": "
                                    + tempChannel
                                    + "(new)    "
                                    + channel
                                    + "(old)");
                    InformaUtils.copyChannelProperties(tempChannel, channel);
                    builder.update(channel);
                    channel.setLastUpdated(new Date());
                    // compare with existing items, only add new ones
                    if (tempChannel.getItems().isEmpty()) {
                        logger.warn("No items found in channel " + channel);
                    } else {
                        Iterator it = tempChannel.getItems().iterator();
                        while (it.hasNext()) {
                            ItemIF item = (ItemIF) it.next();
                            if (!channel.getItems().contains(item)) {
                                logger.debug("Found new item: " + item);
                                channel.addItem(builder.createItem(null, item));
                                //                                                              }
                            }
                        } // while more items
                    }
                    builder.endTransaction();
                }
            } catch (ParseException pe) {
                incrementProblems(pe);
            } catch (IOException ioe) {
                incrementProblems(ioe);
            } catch (ChannelBuilderException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Increases the count of problems occurred so far and put out an error
     * message based on the given exception.
     */
    private void incrementProblems(Exception e) {
        info.increaseProblemsOccurred(e);
        if (info.shouldDeactivate()) {
            logger.warn(
                    "Deactivating channel after "
                            + info.getNrProblemsOccurred()
                            + " problems occurred.");
            registry.deactivateChannel(channel);
        }
        logger.warn(e);
    }

}
