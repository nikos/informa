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
public class Atom030Settings implements CacheSettingsIF {

    //private static Log logger = LogFactory.getLog(Atom030Settings.class);

    private long defaultTtl = DEFAULT_TTL;

    /**
     *
     */
    public void setDefaultTtl(long defaultTtl) {
        this.defaultTtl = defaultTtl;
    }

    /**
     * return in order of preference: feed producer ttl (if exists and < wantedTtl)
     * wantedTtl (if exists) defaultTtl (if exists)
     */
    public long getTtl(ChannelIF channel, long ttlms) {
        //TODO : correct this ... getTtl() when atom parsing complete
        if (channel.getTtl() > 0) {
            long channelTtl = channel.getTtl() * (60 * 1000); // ttl in feed in minutes
            if (ttlms > channelTtl) {
                return ttlms;
            }
            return channelTtl;
        }

        if (ttlms > MINIMAL_TTL)
            return ttlms;

        return this.defaultTtl;

    }
}
