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
 * This interface is implemented by objects representing
 * the GUID of a news item.
 *
 * @author Michael Harhen
 */
public interface ItemGuidIF extends WithIdMIF {

    String getLocation();

    void setLocation(String location);

    /**
     * @return indicates whether the guid is a permalink.
     */
    boolean isPermaLink();

    void setPermaLink(boolean permaLink);

}
