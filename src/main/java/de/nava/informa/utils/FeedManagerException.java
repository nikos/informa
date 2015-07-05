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

/**
 * Exception class, which is used if an invalid feed is tried to be loaded.
 *
 * @author Sam Newman
 * @see FeedManager
 */
public class FeedManagerException extends Exception {

    private static final long serialVersionUID = -1982751404099834335L;

    public FeedManagerException(Exception e) {
        super(e.getMessage());
        initCause(e);
    }

}
