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
 * Meta-, or markerinterface, specifying objects, having <strong>title</strong>.
 *
 * @author <a href="mailto:alexei@matiouchkine.de">Alexei Matiouchkine</a>
 */
public interface WithTitleMIF {

    /**
     * @return the title of the object
     */
    String getTitle();

    /**
     * @param title the title of the object to be set
     */
    void setTitle(String title);

}
