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
 * Meta-, or marker interface, specifying objects, having <strong>creator</strong>.
 * Creator is the part of Dublin Core Metadata.
 *
 * @author <a href="mailto:alexei@matiouchkine.de">Alexei Matiouchkine</a>
 */
public interface WithCreatorMIF {

    /**
     * @return Creator of the object. Returns <code>null</code> if nothing
     * appropriate found.
     * <p>
     * For RSS 0.91 and 2.0 feeds: return text under managingEditor element For
     * RSS 1.0 feed: first checks if creator element exists as dublin core
     * metadata, and then falls back to managingEditor element For RSS 2.0
     * entries: checks the author element for each entry or fallback to creator
     * metadata element from dublin core
     * <p>
     * For Atom 0.3 and 1.0 feeds: checks author element. For Atom 1.0, multiple
     * authors are possible, in which case we return a list of authors delimited
     * by semicolon. Note that the author name itself may contain a semicolon, in
     * which case seperation of author names by splitting at every semicolon will
     * not work.
     */
    String getCreator();

    /**
     * @param creator the creator of the object to be set
     */
    void setCreator(String creator);

}
