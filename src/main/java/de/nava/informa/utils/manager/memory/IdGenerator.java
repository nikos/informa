//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils.manager.memory;

/**
 * Generator of ID sequence. Each application run it starts from 1. We don't need to
 * have unique identifiers across invocations and the implemented approach looks
 * sufficient.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public final class IdGenerator {
    private static long currentId = 1;

    /**
     * Hidden utility class constructor.
     */
    private IdGenerator() {
    }

    /**
     * Returns next available ID value.
     *
     * @return available ID.
     */
    public static long getNextId() {
        return currentId++;
    }
}
