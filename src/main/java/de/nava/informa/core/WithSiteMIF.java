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
 * Meta-, or markerinterface, specifying objects, having <strong>site url,
 * description text and copyright information</strong>.
 *
 * @author <a href="mailto:alexei@matiouchkine.de">Alexei Matiouchkine</a>
 */
public interface WithSiteMIF {

    /**
     * @return the link to the site where more informaion may be obtained
     */
    URL getSite();

    /**
     * @param site the link to the site to be set
     */
    void setSite(URL site);

    /**
     * @return copyright information, if it's presented on the site
     */
    String getCopyright();

    /**
     * @param copyright the copyright information to be set
     */
    void setCopyright(String copyright);

}
