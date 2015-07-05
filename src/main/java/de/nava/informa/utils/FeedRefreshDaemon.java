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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Use a Timer and TimerTask to periodically refresh feeds added by
 * FeedManager.
 *
 * @author jga
 */
public class FeedRefreshDaemon {

    private static Log logger = LogFactory.getLog(FeedRefreshDaemon.class);

    private Timer refreshTimer = null;

    public FeedRefreshDaemon() {
        logger.info("FeedRefresh Daemon instancied");
        this.refreshTimer = new Timer(true);
    }

    public void addFeed(FeedManagerEntry feed) {
        FeedRefreshTask refreshTask = new FeedRefreshTask();
        refreshTask.setFeedME(feed);
        // verifying every 5 minutes
        logger.info("scheduling new feed ");
        Date fiveMinuteLater = new Date(new Date().getTime() + (5 * 60 * 1000));

        this.refreshTimer.schedule(refreshTask, fiveMinuteLater, (5 * 60 * 1000));
    }

    private class FeedRefreshTask extends TimerTask {

        FeedManagerEntry f = null;

        int nbError = 0;

        public void setFeedME(FeedManagerEntry feedME) {
            f = feedME;
        }

        public void run() {
            try {
                f.getFeed(); // call isOutofDate and optionnaly fetch new feed
                logger.debug("feed refreshed" + f.getFeed().getLocation());
            } catch (Exception e) {
                this.nbError++;
                logger.warn("Error retrieving feed" + f.toString() + " " + e);
            }

        }
    }

}