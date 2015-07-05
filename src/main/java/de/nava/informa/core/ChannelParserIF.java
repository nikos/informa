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

import org.jdom2.Element;

/**
 * Interface for a parser which reads in document instances according
 * to some specific channel format specification and generates a news
 * channel object.
 *
 * @author Italo Borssatto
 */
public interface ChannelParserIF {

    /**
     * Method that implements the parser.
     */
    ChannelIF parse(ChannelBuilderIF cBuilder, Element channel)
            throws ParseException;

}
