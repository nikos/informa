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

import java.util.Date;

/**
 * This interface is implemented by objects representing the
 * source of a news item.
 *
 * @author Michael Harhen
 */
public interface ItemSourceIF extends WithNameMIF {

    String getLocation();

    void setLocation(String location);

    /**
     * @return The time was published by the original source
     */
    Date getTimestamp();

    void setTimestamp(Date timestamp);

}
