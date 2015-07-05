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

/**
 * Meta-, or marker interface, specifying objects, having <strong>channel</strong>.
 *
 * @author <a href="mailto:alexei@matiouchkine.de">Alexei Matiouchkine</a>
 */
public interface WithChannelMIF {

    /**
     * @return channel, this object is related to
     */
    ChannelIF getChannel();

    /**
     * @param channel the channel, this object is related to
     */
    void setChannel(ChannelIF channel);

}
