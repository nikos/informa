//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.StringReader;

/**
 * An EntityResolver that resolves the DTD without actually reading
 * the separate file.
 *
 * @author Niko Schmuck (niko@nava.de)
 */
public class NoOpEntityResolver implements EntityResolver {

    private static Log logger = LogFactory.getLog(NoOpEntityResolver.class);

    public InputSource resolveEntity(String publicId, String systemId) {
        if (logger.isDebugEnabled()) {
            logger.debug("publicId: " + publicId +
                    ", systemId: " + systemId);
        }
        return new InputSource(new StringReader(""));
    }

}
