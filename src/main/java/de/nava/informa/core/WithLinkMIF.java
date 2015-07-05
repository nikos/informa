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
 * Meta-, or markerinterface, specifying objects, having <strong>link</strong>.
 *
 * @author <a href="mailto:alexei@matiouchkine.de">Alexei Matiouchkine</a>
 */
public interface WithLinkMIF {

    /**
     * @return The URL where the request action should be sent to
     */
    URL getLink();

    /**
     * @param link the URL where the request action should be sent to
     */
    void setLink(URL link);

}
