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
 * Meta-, or marker interface, specifying objects, having <strong>description</strong>.
 *
 * @author <a href="mailto:alexei@matiouchkine.de">Alexei Matiouchkine</a>
 */
public interface WithDescriptionMIF {

    /**
     * @return description of the object
     */
    String getDescription();

    /**
     * @param description the description of the object to be set
     */
    void setDescription(String description);

}
