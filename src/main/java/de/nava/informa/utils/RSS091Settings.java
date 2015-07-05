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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Jean-Guy Avelin
 */
public class RSS091Settings implements CacheSettingsIF {

    private static Log logger = LogFactory.getLog(RSS100Settings.class);

    private long defaultTtl = DEFAULT_TTL;

    public void setDefaultTtl(long defaultTtl) {
        this.defaultTtl = defaultTtl;
    }

    /**
     * return the ttl (in order of preference) wantedTtl (if exists) defaultTtl
     */
    public long getTtl(ChannelIF channel, long Ttlms) {
        logger.info("getTtl call RSS091 ask:" + Ttlms + " def:" + defaultTtl
                + " feed :" + channel.toString());
        if (Ttlms > MINIMAL_TTL) {

            return Ttlms;
        } else {
            return defaultTtl;
        }
    }

}