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

import de.nava.informa.core.ChannelFormat;
import de.nava.informa.core.ChannelIF;

/**
 * delegation class for the various CacheSettingsIF implementation.
 * <p>
 * default behavior : ttl (or update period) in feed is always respected.
 * without such indication, the ttl specified by the informa user is used.
 *
 * @author Jean-Guy Avelin
 */
public class CacheSettings implements CacheSettingsIF {

    static private CacheSettingsIF v_091;
    static private CacheSettingsIF v_100;
    static private CacheSettingsIF v_200;
    static private CacheSettingsIF v_A030;

    /* default settings */
  /* can read properties file to instantiate alternative implementations*/
  /* TODO : property file handling */
    static {
        v_091 = new RSS091Settings();
        v_100 = new RSS100Settings();
        v_200 = new RSS200Settings();
        v_A030 = new Atom030Settings();
    }

    /**
     * determine ttl for the feed, depending on ttl specified by the
     * feed producer and the ttl wanted by the informa user.
     */
    public long getTtl(ChannelIF channel, long ttl) {

        if (channel.getFormat().equals(ChannelFormat.RSS_0_91)
                || channel.getFormat().equals(ChannelFormat.RSS_0_92)
                || channel.getFormat().equals(ChannelFormat.RSS_0_93)
                || channel.getFormat().equals(ChannelFormat.RSS_0_94)) {
            return v_091.getTtl(channel, ttl);
        } else if (channel.getFormat().equals(ChannelFormat.RSS_1_0)) {
            return v_100.getTtl(channel, ttl);
        } else if (channel.getFormat().equals(ChannelFormat.RSS_2_0)) {
            return v_200.getTtl(channel, ttl);
        } else if (channel.getFormat().equals(ChannelFormat.ATOM_0_3)) {
            return v_A030.getTtl(channel, ttl);
        }

        return CacheSettingsIF.DEFAULT_TTL;
    }

    /**
     * TODO : remove ?
     */
    public void setDefaultTtl(long defTtl) {
        v_091.setDefaultTtl(defTtl);
        v_100.setDefaultTtl(defTtl);
        v_200.setDefaultTtl(defTtl);
        v_A030.setDefaultTtl(defTtl);
    }

    public void setDefaultTtl(String type, long defTtl) {
        if (ChannelFormat.RSS_0_91.name().equals(type)
                || ChannelFormat.RSS_0_92.name().equals(type)
                || ChannelFormat.RSS_0_93.name().equals(type)
                || ChannelFormat.RSS_0_94.name().equals(type)) {
            v_091.setDefaultTtl(defTtl);
        } else  if (ChannelFormat.RSS_1_0.name().equals(type)) {
            v_100.setDefaultTtl(defTtl);
        } else if (ChannelFormat.RSS_2_0.name().equals(type)) {
            v_200.setDefaultTtl(defTtl);
        } else if (ChannelFormat.ATOM_0_3.name().equals(type)) {
            v_A030.setDefaultTtl(defTtl);
        }
    }
}
