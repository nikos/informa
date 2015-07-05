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
 * Exception which is thrown if a syntax problem is encountered while a document
 * instance (like a channel description) is read in.
 *
 * @author Niko Schmuck (niko@nava.de)
 */
public class ParseException extends Exception {

    private static final long serialVersionUID = -2605721941178879488L;

    public ParseException() {
        super();
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable cause) {
        // Java 1.3 does not support this constructor
        super("ParseException: " + cause.getMessage());
    }

}
