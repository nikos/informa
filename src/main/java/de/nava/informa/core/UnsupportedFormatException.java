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
 * An exception thrown when no appropiate parser is available for a
 * specific document instance.
 *
 * @author Niko Schmuck (niko@nava.de)
 */
public class UnsupportedFormatException extends ParseException {

    private static final long serialVersionUID = -9203172795373959253L;

    public UnsupportedFormatException() {
        super();
    }

    public UnsupportedFormatException(String message) {
        super(message);
    }

    public UnsupportedFormatException(Throwable cause) {
        // Java 1.3 does not support this constructor
        super("UnsupportedFormatException: " + cause.getMessage());
    }

}
