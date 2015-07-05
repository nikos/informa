//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils.manager;

/**
 * Exception thrown by <code>PersistenceManagerIF</code> implementations.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public class PersistenceManagerException extends Exception {

    private static final long serialVersionUID = -3142565020702646056L;

    /**
     * Creates exception.
     */
    public PersistenceManagerException() {
    }

    /**
     * Creates exception.
     *
     * @param message description message.
     */
    public PersistenceManagerException(String message) {
        super(message);
    }

    /**
     * Creates exception.
     *
     * @param cause original cause of exception.
     */
    public PersistenceManagerException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates exception.
     *
     * @param message description message.
     * @param cause   original cause of exception.
     */
    public PersistenceManagerException(String message, Throwable cause) {
        super(message, cause);
    }

}
