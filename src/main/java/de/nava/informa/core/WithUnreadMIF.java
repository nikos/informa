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
 * Meta-, or markerinterface, specifying objects,
 * having <strong>Unread</strong>.
 *
 * @author <a href="mailto:alexei@matiouchkine.de">Alexei Matiouchkine</a>
 */
public interface WithUnreadMIF {

    /**
     * @return boolean indicating whether this item is currently unread.
     */
    boolean getUnRead();

    /**
     * @param val boolean to indicate whether this item is unread or not.
     */
    void setUnRead(boolean val);

}
