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

import java.util.Collection;

/**
 * Meta-, or markerinterface, specifying objects, having <strong>categories
 * collection</strong> behind.
 *
 * @author <a href="mailto:alexei@matiouchkine.de">Alexei Matiouchkine</a>
 */
public interface WithCategoriesMIF {

    /**
     * Gets the assigned category objects for this channel.
     *
     * @return A collection of CategoryIF objects, in the case of no
     * assigned categories an empty collection is returned..
     */
    Collection getCategories();

    void setCategories(Collection<CategoryIF> categories);

    void addCategory(CategoryIF category);

    void removeCategory(CategoryIF category);

}
