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

import java.net.URL;

/**
 * Meta-, or markerinterface, specifying objects, having <strong>location</strong>.
 *
 * @author <a href="mailto:alexei@matiouchkine.de">Alexei Matiouchkine</a>
 */
public interface WithLocationMIF {

    /**
     * @return The URL where this channel can be retrieved from.
     */
    URL getLocation();

    /**
     * @param location The URL where this channel can be retrieved from.
     */
    void setLocation(URL location);

}
