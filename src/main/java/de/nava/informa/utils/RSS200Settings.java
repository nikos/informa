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

import de.nava.informa.core.ChannelIF;

/**
 * @author Jean-Guy Avelin
 */
public class RSS200Settings implements CacheSettingsIF {

    //private static Log logger = LogFactory.getLog(RSS200Settings.class);

    private long defaultTtl = DEFAULT_TTL;

    /**
     * Set default time-to-live value.
     */
    public void setDefaultTtl(long defaultTtl) {
        this.defaultTtl = defaultTtl;
    }

    /**
     * Returns in order of preference: feed producer ttl (if exists and &lt; wantedTtl)
     * wantedTtl (if exists) defaultTtl (if exists)
     */
    public long getTtl(ChannelIF channel, long Ttlms) {
        if (channel.getTtl() > 0) {
            long channelTtl = channel.getTtl() * (60 * 1000); // ttl in feed in minutes
            if (Ttlms > channelTtl) {
                return Ttlms;
            }
            return channelTtl;
        }

        if (Ttlms > MINIMAL_TTL)
            return Ttlms;

        return defaultTtl;

    }
}