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
 * This interface is implemented by objects representing categories in
 * which channels could be organised in the news channel object model.</p>
 * <p>
 * <p>{@link WithChildrenMIF#getChildren()} collection contains here nested
 * CategoryIF objects</p>
 *
 * @author Niko Schmuck (niko@nava.de)
 */
public interface CategoryIF extends WithIdMIF, WithTitleMIF, WithChildrenMIF<CategoryIF> {

    String getDomain();

    void setDomain(String domain);

    CategoryIF getParent();

    void setParent(CategoryIF parent);

    void addChild(CategoryIF child);

    void removeChild(CategoryIF child);

}
