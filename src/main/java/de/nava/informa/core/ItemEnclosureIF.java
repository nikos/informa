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

import java.io.Serializable;

/**
 * This interface is implemented by objects representing an enclosure.</p>
 * <p>{@link WithLocationMIF#getLocation()} here denotes the source of the enclosure</p>
 *
 * @author Michael Harhen
 */
public interface ItemEnclosureIF extends Serializable, WithLocationMIF {

    /**
     * @return The length of the enclosure
     */
    int getLength();

    void setLength(int length);

    /**
     * @return The time was published by the original source
     */
    String getType();

    void setType(String type);

}
