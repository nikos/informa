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
import de.nava.informa.core.ChannelUpdatePeriod;

/**
 * @author Jean-Guy Avelin
 */
public class RSS100Settings implements CacheSettingsIF {

    //private static Log logger = LogFactory.getLog(RSS100Settings.class);

    private long defaultTtl = DEFAULT_TTL;

    public void setDefaultTtl(long defaultTtl) {
        this.defaultTtl = defaultTtl;
    }

    /**
     * Returns the ttl (in order of preference) feed producer ttl (if exists)
     * wantedTtl (if exists) defaultTtl (if exists).
     */
    public long getTtl(ChannelIF channel, long ttlInMs) {
        ChannelUpdatePeriod updatePeriod = channel.getUpdatePeriod();
        int updateFrequency = channel.getUpdateFrequency();

        if (updatePeriod == null) {
            if (ttlInMs > MINIMAL_TTL)
                return ttlInMs;
            return this.defaultTtl;
        }

        if (updateFrequency < 0) {
            updateFrequency = 1;
        }

        long timeToExpire = updatePeriod.getMsInPeriod() / updateFrequency;

        if (ttlInMs > timeToExpire) {
            return ttlInMs;
        }
        return timeToExpire;
    }

}