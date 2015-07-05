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
public interface CacheSettingsIF {

    long MILLISECONDS_IN_HOUR = 3600000L;
    long MILLISECONDS_IN_DAY = 86400000L;
    long MILLISECONDS_IN_MONTH = 2419200000L;
    long MILLISECONDS_IN_YEAR = 31536000000L;
    long MINIMAL_TTL = 300000L; //5 minutes

    //private long Ttl = 0L;

    long DEFAULT_TTL = MILLISECONDS_IN_HOUR;

    long getTtl(ChannelIF channel, long ttl);

    void setDefaultTtl(long ttl);

}