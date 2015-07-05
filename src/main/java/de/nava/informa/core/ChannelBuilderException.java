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
 * Indicates that a channel builder encountered an unexpected condition.
 *
 * @author Niko Schmuck (niko@nava.de)
 */
public class ChannelBuilderException extends Exception {

    private static final long serialVersionUID = -2214051590854155673L;

    public ChannelBuilderException() {
        super();
    }

    public ChannelBuilderException(String message) {
        super(message);
    }

    public ChannelBuilderException(Throwable cause) {
        super("ChannelBuilderException: " + cause.getMessage());
    }

}
