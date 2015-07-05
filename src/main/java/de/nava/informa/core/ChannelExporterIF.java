//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.core;

import java.io.IOException;

/**
 * A channel exporter is used to write channel objects in an
 * implementation dependent way to an implicit destination.</p>
 *
 * @author Niko Schmuck (niko@nava.de)
 */
public interface ChannelExporterIF {

    /**
     * Writes the given channel to an implicit implementation dependent
     * destination.
     *
     * @param channel - The channel to be exported/written;
     *                an object implementing ChannelIF.
     * @throws IOException - Thrown if writing the channel fails.
     */
    void write(ChannelIF channel) throws IOException;

}
