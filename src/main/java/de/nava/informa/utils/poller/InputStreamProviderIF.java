//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils.poller;

import de.nava.informa.core.ChannelIF;

import java.io.IOException;
import java.io.InputStream;

/**
 * Interface of <code>InputStream</code> provider which is intended to
 * return input stream for a given channel.
 */
public interface InputStreamProviderIF {

    /**
     * Return <code>InputStream</code> to be used for reading given channel.
     *
     * @param channel  channel we are going to read.
     * @param activity activity name (like, "Fetching" or "Detecting format").
     * @return initialized input stream ready for reading.
     * @throws java.io.IOException in case of any problems.
     */
    InputStream getInputStreamFor(ChannelIF channel, String activity) throws IOException;
}
